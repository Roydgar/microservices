package com.example.microservices.order.config;

import com.example.microservices.order.model.event.OrderCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class OrderPublisherConfig {

    @Bean
    public Sinks.Many<OrderCreatedEvent> orderSinks(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean("orderCreatedSupplier")
    public Supplier<Flux<OrderCreatedEvent>> orderCreatedSupplier(Sinks.Many<OrderCreatedEvent> sinks){
       return sinks::asFlux;
    }
}