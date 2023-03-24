package com.example.microservices.customer.controller;

import com.example.microservices.customer.model.CreateCustomerRequestDto;
import com.example.microservices.customer.model.CustomerDto;
import com.example.microservices.customer.service.CustomerService;
import com.example.microservices.customer.service.mapper.CustomerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping("/{customerId}")
    public Mono<CustomerDto> getCustomerById(@PathVariable UUID customerId) {
        return customerService.findById(customerId)
                .map(customerMapper::mapToDto);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<CustomerDto> createCustomer(@RequestBody @Valid CreateCustomerRequestDto customerRequest) {
        return customerService.save(customerRequest)
                .map(customerMapper::mapToDto);
    }
}
