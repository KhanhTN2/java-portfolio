package com.manven.orderservice.service;

import com.manven.orderservice.domain.Order;
import com.manven.orderservice.domain.OrderStatus;
import com.manven.orderservice.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    public OrderService(OrderRepository orderRepository, PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.paymentClient = paymentClient;
    }

    public Order createOrder(String customerId, BigDecimal amount) {
        Order order = new Order(customerId, amount, OrderStatus.PENDING, Instant.now());
        boolean authorized = paymentClient.authorize(customerId, amount)
            .toCompletableFuture()
            .join();
        order.setStatus(authorized ? OrderStatus.PAID : OrderStatus.FAILED);
        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<Order> getOrdersByCustomer(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
