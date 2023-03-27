package com.example.microservices.order.contract;

import com.example.microservices.order.controller.OrderController;
import com.example.microservices.order.model.Order;
import com.example.microservices.order.repository.OrderRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseContractTest {

    private static final UUID orderId = UUID.fromString("77e5e74f-6e2d-4a25-bcfb-30f4c550ec8f");

    @Autowired
    private OrderController orderController;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(orderController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);

        Order order = Order.builder()
                .id(orderId)
                .customerId(UUID.randomUUID())
                .status(Order.Status.CREATED)
                .createdAt(Instant.MAX)
                .updatedAt(Instant.MAX)
                .version(0)
                .build();

        orderRepository.save(order).subscribe();
    }
}
