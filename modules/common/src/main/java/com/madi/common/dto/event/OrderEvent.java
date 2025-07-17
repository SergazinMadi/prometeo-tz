package com.madi.common.dto.event;

import com.madi.common.dto.dto.OrderDto;

import java.time.Instant;
import java.util.UUID;

public record OrderEvent(
        String eventType,
        UUID orderId,
        Object payload,
        Instant timestamp
){
}