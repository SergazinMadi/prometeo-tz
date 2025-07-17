package com.madi.orderquery.service.impl;

import com.madi.orderquery.db.entity.Item;
import com.madi.orderquery.db.entity.Order;
import com.madi.orderquery.db.repository.ItemRepository;
import com.madi.orderquery.db.repository.OrderRepository;
import com.madi.orderquery.dto.dto.OrderDto;
import com.madi.orderquery.dto.mapper.ItemMapper;
import com.madi.orderquery.dto.mapper.OrderMapper;
import com.madi.orderquery.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public OrderDto getOrderById(UUID orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        OrderDto orderDto = orderMapper.toDto(order);
        List<Item> items = itemRepository.findAllByOrder(order);
        orderDto.setItems(items.stream().map(itemMapper::toDto).collect(Collectors.toList()));
        return orderDto;
    }
}
