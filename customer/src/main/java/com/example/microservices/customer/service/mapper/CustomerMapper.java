package com.example.microservices.customer.service.mapper;


import com.example.microservices.customer.model.CreateCustomerRequestDto;
import com.example.microservices.customer.model.Customer;
import com.example.microservices.customer.model.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer mapToCustomer(CreateCustomerRequestDto request) {
        return Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
    }

    public CustomerDto mapToDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
    }
}
