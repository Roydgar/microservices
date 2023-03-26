package org.example.microservices.model.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCustomerRequestDto {
    private String firstName;
    private String lastName;
    private String email;
}
