package com.madi.common.dto.event;


import java.time.Instant;
import java.util.UUID;

public record OrderEvent(
        String eventType,
        UUID orderId,
        Object payload,
        Instant timestamp
){}