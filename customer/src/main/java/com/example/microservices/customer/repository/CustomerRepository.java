package com.example.microservices.customer.repository;

import com.example.microservices.customer.model.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CustomerRepository extends R2dbcRepository<Customer, UUID> {
    Mono<Boolean> existsByEmail(String email);
}
