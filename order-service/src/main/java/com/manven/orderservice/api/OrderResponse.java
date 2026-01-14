package com.manven.orderservice.api;

import com.manven.orderservice.domain.Order;
import java.math.BigDecimal;
import java.time.Instant;

public record OrderResponse(
    Long id,
    String customerId,
    BigDecimal amount,
    String status,
    Instant createdAt
) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(
            order.getId(),
            order.getCustomerId(),
            order.getAmount(),
            order.getStatus().name(),
            order.getCreatedAt()
        );
    }
}
