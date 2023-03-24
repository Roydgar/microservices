package com.example.microservices.order.service.mapper;


import com.example.microservices.order.model.Order;
import com.example.microservices.order.model.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto mapToDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .createdAt(order.getCreatedAt())
                .status(order.getStatus())
                .build();
    }
}
