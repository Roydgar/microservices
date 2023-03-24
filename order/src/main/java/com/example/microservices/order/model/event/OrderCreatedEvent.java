package com.example.microservices.order.model.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class OrderCreatedEvent {
    private final UUID orderId;
    private final UUID consumerId;
}
