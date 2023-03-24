package com.example.microservices.order.service;


import com.example.microservices.order.exception.OrderNotFoundException;
import com.example.microservices.order.model.CreateOrderRequestDto;
import com.example.microservices.order.model.Order;
import com.example.microservices.order.repository.OrderRepository;
import com.example.microservices.order.service.event.OrderStatusPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.example.microservices.order.model.Order.Status.CANCELLED;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public Mono<Order> saveOrder(CreateOrderRequestDto createRequest) {
        Order order = Order.builder()
                .customerId(createRequest.getCustomerId())
                .status(Order.Status.CREATED)
                .build();

        return orderRepository.save(order)
                .doOnSuccess(orderStatusPublisher::publishOrderCreatedEvent);
    }

    public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    public Mono<Order> findById(UUID orderId) {
        log.info("Looking for an order with id {}", orderId);

        return orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new OrderNotFoundException("Customer with id " + orderId + " is not found")));
    }

    public Mono<Order> updateOrder(Order order) {
        log.info("Updating order with id {}", order.getId());
        return orderRepository.save(order);
    }
}
