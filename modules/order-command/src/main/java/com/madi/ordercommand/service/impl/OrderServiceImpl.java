package com.madi.ordercommand.service.impl;

import com.madi.common.dto.event.OrderEvent;
import com.madi.ordercommand.db.entity.Item;
import com.madi.ordercommand.db.entity.Order;
import com.madi.ordercommand.db.repository.ItemRepository;
import com.madi.ordercommand.db.repository.OrderRepository;
import com.madi.ordercommand.dto.dto.ItemDto;
import com.madi.ordercommand.dto.dto.OrderDto;
import com.madi.ordercommand.dto.mapper.ItemMapper;
import com.madi.ordercommand.dto.mapper.OrderMapper;
import com.madi.ordercommand.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public UUID createOrder(OrderDto orderDto) {
        Order order = orderRepository.save(orderMapper.toEntity(orderDto));
        List<Item> itemList = saveItemsWithOrder(orderDto.getItems(), order);
        sendKafkaEvent("OrderCreated", order, itemList);
        return order.getId();
    }


    @Transactional
    @Override
    public OrderDto updateOrder(OrderDto orderDto, UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        order.setCurrency(orderDto.getCurrency());
        order.setCustomerId(orderDto.getCustomerId());
        itemRepository.deleteAllByOrder(order);
        List<Item> itemList = saveItemsWithOrder(orderDto.getItems(), order);
        order = orderRepository.save(order);
        sendKafkaEvent("OrderUpdated", order, itemList);
        return orderMapper.toDto(order);
    }


    private List<Item> saveItemsWithOrder(List<ItemDto> itemDtos, Order order) {
        return itemRepository.saveAll(
                itemDtos.stream().map(i -> {
                    Item item = itemMapper.toEntity(i);
                    item.setOrder(order);
                    return item;
                }).collect(Collectors.toList())
        );
    }

    private void sendKafkaEvent(String eventType, Order order, List<Item> items) {
        OrderDto orderDto = orderMapper.toDto(order);
        orderDto.setItems(items.stream().map(itemMapper::toDto).collect(Collectors.toList()));
        kafkaTemplate.send("orders", new OrderEvent(
                eventType,
                order.getId(),
                orderDto,
                LocalDateTime.now()
        ));
    }

}
