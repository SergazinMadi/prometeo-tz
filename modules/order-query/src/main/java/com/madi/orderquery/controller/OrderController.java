package com.madi.orderquery.controller;

import com.madi.orderquery.dto.dto.OrderDto;
import com.madi.orderquery.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Order Query API", description = "Получение информации о заказах")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить заказ по ID",
            description = "Возвращает информацию о заказе по заданному UUID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ найден"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    public ResponseEntity<OrderDto> getOrderById(
            @Parameter(description = "UUID заказа", required = true)
            @PathVariable("id") UUID orderId
    ) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
}
