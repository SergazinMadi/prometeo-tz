package com.madi.orderquery.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madi.common.dto.event.OrderEvent;
import com.madi.orderquery.db.entity.Item;
import com.madi.orderquery.db.entity.Order;
import com.madi.orderquery.db.repository.ItemRepository;
import com.madi.orderquery.db.repository.OrderRepository;
import com.madi.orderquery.dto.dto.ItemDto;
import com.madi.orderquery.dto.dto.OrderDto;
import com.madi.orderquery.dto.mapper.ItemMapper;
import com.madi.orderquery.dto.mapper.OrderMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@KafkaListener(topics = "orders")
public class KafkaConsumerService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ObjectMapper objectMapper;

    @KafkaHandler
    public void handleOrderEvent(OrderEvent orderEvent){
        OrderDto orderDto = objectMapper.convertValue(orderEvent.payload(), OrderDto.class);
        switch (orderEvent.eventType()) {
            case "OrderCreated":
                handleCreated(orderDto);
                break;
            case "OrderUpdated":
                handleUpdated(orderDto, orderEvent.orderId());
                break;
        }

    }

    private void handleCreated(OrderDto orderDto) {
        Order order = orderRepository.save(orderMapper.toEntity(orderDto));
        processItems(orderDto.getItems(), order);
    }

    private void handleUpdated(OrderDto orderDto, UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        order.setCurrency(orderDto.getCurrency());
        order.setCustomerId(orderDto.getCustomerId());
        itemRepository.deleteAllByOrder(order);
        Order updatedOrder = orderRepository.save(order);
        processItems(orderDto.getItems(), updatedOrder);
    }

    private void processItems(List<ItemDto> itemDtos, Order order) {
        List<Item> itemList = itemRepository.saveAll(
                itemDtos.stream().map(i -> {
                    Item item = itemMapper.toEntity(i);
                    item.setOrder(order);
                    return item;
                }).collect(Collectors.toList())
        );
        OrderDto savedOrder = orderMapper.toDto(order);
        savedOrder.setItems(
                itemList.stream().map(itemMapper::toDto).collect(Collectors.toList())
        );
    }

}
