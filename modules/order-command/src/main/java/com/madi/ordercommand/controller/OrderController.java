package com.madi.ordercommand.controller;

import com.madi.ordercommand.dto.dto.OrderDto;
import com.madi.ordercommand.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<UUID> createOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.created(URI.create("/orders")).body(orderService.createOrder(orderDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto, @PathVariable("id") UUID id){
        return ResponseEntity.ok(orderService.updateOrder(orderDto, id));
    }
}
