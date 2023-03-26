package org.example.microservices.model.order;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class OrderDto {
    private UUID id;
    private UUID customerId;
    private String status;
    private String cancelReason;
}
