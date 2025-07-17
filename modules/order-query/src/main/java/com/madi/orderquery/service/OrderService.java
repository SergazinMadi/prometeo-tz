package com.madi.orderquery.service;

import com.madi.orderquery.dto.dto.OrderDto;

import java.util.UUID;

public interface OrderService {
    OrderDto getOrderById(UUID orderId);
}
