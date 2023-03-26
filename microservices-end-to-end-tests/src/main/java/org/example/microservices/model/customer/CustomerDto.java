package org.example.microservices.model.customer;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CustomerDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
