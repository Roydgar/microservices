package com.example.microservices.order.service.event;

import com.example.microservices.order.model.event.CustomerVerificationEvent;
import com.example.microservices.order.service.OrderManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static com.example.microservices.order.model.Order.CancelReason.CUSTOMER_VERIFICATION_FAILED;

@Slf4j
@Component("customerVerificationEventConsumer")
@RequiredArgsConstructor
public class CustomerVerificationEventConsumer implements Consumer<CustomerVerificationEvent> {

    private final OrderManagementService orderManagementService;

    @Override
    public void accept(CustomerVerificationEvent event) {
        log.info("Received customer verification event. Order id: {}. Customer id: {}. Verification status: {}",
                event.getOrderId(), event.getCustomerId(), event.isSuccessful());

        if (event.isSuccessful()) {
            orderManagementService.confirmOrder(event.getOrderId());
        } else {
            orderManagementService.cancelOrder(event.getOrderId(), CUSTOMER_VERIFICATION_FAILED);
        }
    }
}
