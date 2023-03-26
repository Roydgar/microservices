package org.example.microservices.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.example.microservices.model.order.CreateOrderRequestDto;
import org.example.microservices.model.order.OrderDto;

import java.util.UUID;

public interface OrderClient {
    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    OrderDto create(CreateOrderRequestDto createRequest);

    @RequestLine("GET /{id}")
    OrderDto findById(@Param("id") UUID uuid);
}