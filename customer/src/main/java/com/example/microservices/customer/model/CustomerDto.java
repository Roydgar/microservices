package com.example.microservices.customer.model;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class CustomerDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    private Instant createdAt;
    private Instant updatedAt;
    private int version;

}
