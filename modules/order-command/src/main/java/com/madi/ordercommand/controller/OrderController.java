package com.madi.ordercommand.controller;

import com.madi.ordercommand.dto.dto.OrderDto;
import com.madi.ordercommand.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new order", description = "Creates a new order and returns its UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<UUID> createOrder(
            @RequestBody
            @Parameter(description = "Order data to create", required = true)
            OrderDto orderDto) {
        return ResponseEntity.created(URI.create("/orders"))
                .body(orderService.createOrder(orderDto));
    }

    @Operation(summary = "Update an existing order", description = "Updates the order with the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully updated"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(
            @RequestBody
            @Parameter(description = "Updated order data", required = true)
            OrderDto orderDto,

            @PathVariable("id")
            @Parameter(description = "UUID of the order to update", required = true)
            UUID id) {
        return ResponseEntity.ok(orderService.updateOrder(orderDto, id));
    }
}
