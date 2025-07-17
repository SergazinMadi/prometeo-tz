package com.madi.common.dto.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderEvent(
        String eventType,
        UUID orderId,
        Object payload,
        LocalDateTime timestamp
){}