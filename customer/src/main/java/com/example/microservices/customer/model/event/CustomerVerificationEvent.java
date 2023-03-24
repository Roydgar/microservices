package com.example.microservices.customer.model.event;

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
