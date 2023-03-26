package org.example.microservices.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.example.microservices.client.CustomerClient;
import org.example.microservices.context.ScenarioContext;
import org.example.microservices.model.customer.CreateCustomerRequestDto;
import org.example.microservices.model.customer.CustomerDto;

import java.util.UUID;

@Slf4j
public class CustomerStepDefs {

    private final CustomerClient customerClient;

    @Inject
    public CustomerStepDefs(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @Given("a new customer is created")
    public void aNewCustomerIsCreated() {
        CreateCustomerRequestDto request = CreateCustomerRequestDto.builder()
                .firstName("e2e-customer-first-name")
                .lastName("e2e-customer-last-name")
                .email("e2e-customer" + UUID.randomUUID() + "@gmail.com")
                .build();

        CustomerDto customerDto = customerClient.create(request);

        log.info("A new customer with id {} is created", customerDto.getId());
        ScenarioContext.INSTANCE.setCustomerDto(customerDto);
    }
}
