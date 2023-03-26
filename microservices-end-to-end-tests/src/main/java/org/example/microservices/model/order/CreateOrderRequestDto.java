package org.example.microservices.model.order;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateOrderRequestDto {
    private UUID customerId;
}
