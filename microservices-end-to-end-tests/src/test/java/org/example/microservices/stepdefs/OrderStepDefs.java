package org.example.microservices.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.example.microservices.client.OrderClient;
import org.example.microservices.context.ScenarioContext;
import org.example.microservices.model.order.CreateOrderRequestDto;
import org.example.microservices.model.order.OrderDto;

import java.util.UUID;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@Slf4j
public class OrderStepDefs {

    private final OrderClient orderClient;

    @Inject
    public OrderStepDefs(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @When("a new order is created for the created customer")
    public void aNewOrderIsCreatedForCreatedCustomer() {
        UUID customerId = ScenarioContext.INSTANCE.getCustomerDto().getId();

        CreateOrderRequestDto createOrderRequest = CreateOrderRequestDto.builder()
                .customerId(customerId)
                .build();

        OrderDto orderDto = orderClient.create(createOrderRequest);

        log.info("A new order with id {} for the customer with id {} is created", orderDto.getId(), orderDto.getCustomerId());
        ScenarioContext.INSTANCE.setOrderDto(orderDto);
    }

    @When("order is created for a non-existing customer")
    public void orderIsCreatedForANonExistingCustomer() {
        CreateOrderRequestDto createOrderRequest = CreateOrderRequestDto.builder()
                .customerId(UUID.randomUUID())
                .build();

        OrderDto orderDto = orderClient.create(createOrderRequest);

        log.info("A new order with id {} for non-existing customer is created", orderDto.getId());
        ScenarioContext.INSTANCE.setOrderDto(orderDto);
    }

    @Then("order is in {string} status")
    public void orderIsInStatus(String expectedStatus) {
        UUID orderId = ScenarioContext.INSTANCE.getOrderDto().getId();

        log.info("Verifying order status to be {}. Order id: {}", expectedStatus, orderId);
        await()
                .pollDelay(1, SECONDS)
                .atMost(5, SECONDS)
                .untilAsserted(() -> assertThat(orderClient.findById(orderId).getStatus()).isEqualTo(expectedStatus));
    }

    @And("cancel reason of the order is empty")
    public void cancelReasonOfTheOrderIsEmpty() {
        UUID orderId = ScenarioContext.INSTANCE.getOrderDto().getId();

        log.info("Verifying order cancel reason to be empty. Order id: {}", orderId);
        await()
                .pollDelay(1, SECONDS)
                .atMost(5, SECONDS)
                .untilAsserted(() -> assertThat(orderClient.findById(orderId).getCancelReason()).isNullOrEmpty());
    }

    @And("cancel reason of the order is {string}")
    public void cancelReasonOfTheOrderIs(String expectedCancelReason) {
        UUID orderId = ScenarioContext.INSTANCE.getOrderDto().getId();

        log.info("Verifying order cancel reason to be {}. Order id: {}", expectedCancelReason, orderId);
        await()
                .pollDelay(1, SECONDS)
                .atMost(5, SECONDS)
                .untilAsserted(() -> assertThat(orderClient.findById(orderId).getCancelReason()).isEqualTo(expectedCancelReason));
    }

}
