package com.example.microservices.order.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrderRequestDto {
    @NotNull
    private UUID customerId;
}
