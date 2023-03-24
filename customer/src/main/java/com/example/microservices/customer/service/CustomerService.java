package com.example.microservices.customer.service;

import com.example.microservices.customer.exception.CustomerAlreadyExistsException;
import com.example.microservices.customer.exception.EntityNotFoundException;
import com.example.microservices.customer.model.CreateCustomerRequestDto;
import com.example.microservices.customer.model.Customer;
import com.example.microservices.customer.repository.CustomerRepository;
import com.example.microservices.customer.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Mono<Customer> save(CreateCustomerRequestDto createRequest) {
        String email = createRequest.getEmail();

        return customerRepository.existsByEmail(email)
                .flatMap(existsByEmail -> existsByEmail
                        ? Mono.error(new CustomerAlreadyExistsException("Customer with email " + email + " already exists"))
                        : saveCustomer(createRequest)
            );
    }

    public Mono<Customer> findById(UUID id) {
        log.info("Looking for a customer with id {}", id);

        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Customer with id " + id + " is not found")));
    }

    private Mono<Customer> saveCustomer(CreateCustomerRequestDto customerRequest) {
        Customer customer = customerMapper.mapToCustomer(customerRequest);
        return customerRepository.save(customer);
    }
}
