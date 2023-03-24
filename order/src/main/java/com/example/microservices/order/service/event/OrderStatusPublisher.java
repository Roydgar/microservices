package com.example.microservices.order.service.event;

import com.example.microservices.order.model.Order;
import com.example.microservices.order.model.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderStatusPublisher {

    private final Sinks.Many<OrderCreatedEvent> orderSinks;

    public void publishOrderCreatedEvent(Order createdOrder) {
        log.info("Publishing an order created event for order with id {}", createdOrder.getId());
        OrderCreatedEvent event = new OrderCreatedEvent(createdOrder.getId(), createdOrder.getCustomerId());

        orderSinks.tryEmitNext(event);
    }
}