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

import java.time.Instant;
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
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public UUID createOrder(OrderDto orderDto){
        List<ItemDto> itemDtos = orderDto.getItems();
        Order order = orderRepository.save(orderMapper.toEntity(orderDto));
        List<Item> itemList = itemRepository.saveAll(itemDtos.stream().map(i -> {
            Item item = itemMapper.toEntity(i);
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList()));
        OrderDto savedOrder = orderMapper.toDto(order);
        savedOrder.setItems(itemList.stream().map(itemMapper::toDto).collect(Collectors.toList()));
        kafkaTemplate.send("orders", new OrderEvent(
                "OrderCreated",
                order.getId(),
                savedOrder,
                Instant.now()));
        return order.getId();
    }

    @Transactional
    public OrderDto updateOrder(OrderDto orderDto, UUID orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        List<ItemDto> itemDtos = orderDto.getItems();
        order.setCurrency(orderDto.getCurrency());
        order.setCustomerId(orderDto.getCustomerId());
        itemRepository.deleteAllByOrder(order);
        List<Item> itemList = itemRepository.saveAll(itemDtos.stream().map(i -> {
            Item item = itemMapper.toEntity(i);
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList()));
        OrderDto savedOrder = orderMapper.toDto(orderRepository.save(order));
        savedOrder.setItems(itemList.stream().map(itemMapper::toDto).collect(Collectors.toList()));
        kafkaTemplate.send("orders", new OrderEvent(
                "OrderUpdated",
                order.getId(),
                savedOrder,
                Instant.now()));
        return savedOrder;
    }
}
