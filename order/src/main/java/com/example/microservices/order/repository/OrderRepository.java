package com.example.microservices.order.repository;


import com.example.microservices.order.model.Order;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends R2dbcRepository<Order, UUID> {
}
