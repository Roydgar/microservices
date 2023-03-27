package com.example.microservices.customer.service.stream;

import com.example.microservices.customer.model.event.CustomerVerificationEvent;
import com.example.microservices.customer.model.event.OrderCreatedEvent;
import com.example.microservices.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Slf4j
@Component("orderEventProcessor")
@RequiredArgsConstructor
public class OrderEventProcessor implements Function<Flux<OrderCreatedEvent>, Flux<CustomerVerificationEvent>> {

    private final CustomerService customerService;

    @Override
    public Flux<CustomerVerificationEvent> apply(Flux<OrderCreatedEvent> orderEventFlux) {
        return orderEventFlux.flatMap(this::processEvent);
    }

    private Mono<CustomerVerificationEvent> processEvent(OrderCreatedEvent orderCreatedEvent) {
        log.info("Received an order event for customer {}", orderCreatedEvent.getConsumerId());

        return customerService.findById(orderCreatedEvent.getConsumerId())
                .map(customer -> createVerificationEvent(orderCreatedEvent, true))
                .onErrorResume(throwable -> Mono.just(createVerificationEvent(orderCreatedEvent, false)));
    }

    private CustomerVerificationEvent createVerificationEvent(OrderCreatedEvent orderCreatedEvent, boolean successful) {
        return new CustomerVerificationEvent(orderCreatedEvent.getConsumerId(), orderCreatedEvent.getOrderId(), successful);
    }
}
