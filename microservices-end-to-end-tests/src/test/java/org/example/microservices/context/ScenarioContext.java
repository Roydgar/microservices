package org.example.microservices.context;


import lombok.Getter;
import lombok.Setter;
import org.example.microservices.model.customer.CustomerDto;
import org.example.microservices.model.order.OrderDto;

@Getter
public enum ScenarioContext {
    INSTANCE;
    @Setter
    private OrderDto orderDto;
    @Setter
    private CustomerDto customerDto;

    public void cleanUp() {
        this.customerDto = null;
        this.orderDto = null;
    }
}
