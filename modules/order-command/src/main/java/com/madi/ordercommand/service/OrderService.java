package com.madi.ordercommand.service;

import com.madi.ordercommand.dto.dto.OrderDto;

import java.util.UUID;

public interface OrderService {
    UUID createOrder(OrderDto orderDto);
    OrderDto updateOrder(OrderDto orderDto, UUID orderId);

}
