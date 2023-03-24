package com.example.microservices.order.model.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class CustomerVerificationEvent {
    private final UUID customerId;
    private final UUID orderId;
    private final boolean successful;
}
