package com.example.microservices.order.service;

import com.example.microservices.order.model.Order;
import com.example.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.microservices.order.model.Order.Status.CANCELLED;
import static com.example.microservices.order.model.Order.Status.CONFIRMED;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final OrderRepository orderRepository;

    public void cancelOrder(UUID orderId, Order.CancelReason reason) {
        orderRepository.findById(orderId)
                .map(order -> {
                    order.setStatus(CANCELLED);
                    order.setCancelReason(reason);
                    return order;
                })
                .doOnSuccess(order -> orderRepository.save(order).subscribe())
                .subscribe(order -> log.info("Cancelled order with id {}. Reason: {}", orderId, reason));
    }

    public void confirmOrder(UUID orderId) {
        this.updateOrderStatus(orderId, CONFIRMED);
    }

    public void updateOrderStatus(UUID orderId, Order.Status status) {
        orderRepository.findById(orderId)
                .map(order -> {
                    order.setStatus(status);
                    return order;
                })
                .doOnSuccess(order -> orderRepository.save(order).subscribe())
                .subscribe(order -> log.info("Successfully updated order status to {}. Order id: {}",
                        order.getStatus(), order.getId()));
    }
}
