package com.example.microservices.customer.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCustomerRequestDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email(regexp =  "^.+@.+\\..+$")
    private String email;
}
