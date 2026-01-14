package com.manven.orderservice.repository;

import com.manven.orderservice.domain.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(String customerId);
}
