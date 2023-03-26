package org.example.microservices.client;

import feign.Headers;
import feign.RequestLine;
import org.example.microservices.model.customer.CreateCustomerRequestDto;
import org.example.microservices.model.customer.CustomerDto;

public interface CustomerClient {
    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    CustomerDto create(CreateCustomerRequestDto createRequest);
}