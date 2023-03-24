package com.example.microservices.order.controller;

import com.example.microservices.order.model.CreateOrderRequestDto;
import com.example.microservices.order.model.OrderDto;
import com.example.microservices.order.service.OrderService;
import com.example.microservices.order.service.mapper.OrderMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public Flux<OrderDto> findAll() {
        return orderService.findAll()
                .map(orderMapper::mapToDto);
    }

    @PostMapping
    public Mono<OrderDto> createOrder(@RequestBody @Valid CreateOrderRequestDto createRequest) {
        return orderService.saveOrder(createRequest)
                .map(orderMapper::mapToDto);
    }
}
