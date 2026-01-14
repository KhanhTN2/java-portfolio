package com.manven.orderservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private String customerId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "created_at")
    private Instant createdAt;

    protected Order() {
    }

    public Order(String customerId, BigDecimal amount, OrderStatus status, Instant createdAt) {
        this.customerId = customerId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
