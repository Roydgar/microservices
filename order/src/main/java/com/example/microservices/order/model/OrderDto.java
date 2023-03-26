package com.example.microservices.order.model;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
public class OrderDto {
    private UUID id;
    private UUID customerId;
    private Order.Status status;
    private Order.CancelReason cancelReason;
    private Instant createdAt;
}
