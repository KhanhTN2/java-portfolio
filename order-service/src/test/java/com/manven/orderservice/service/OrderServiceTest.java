package com.manven.orderservice.service;

import com.manven.orderservice.domain.Order;
import com.manven.orderservice.domain.OrderStatus;
import com.manven.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderServiceTest {

    @Test
    void createOrderPersistsPaidOrder() {
        OrderRepository repository = Mockito.mock(OrderRepository.class);
        PaymentClient paymentClient = Mockito.mock(PaymentClient.class);
        Mockito.when(paymentClient.authorize(Mockito.eq("cust-1"), Mockito.eq(new BigDecimal("10.00"))))
            .thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(repository.save(Mockito.any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OrderService service = new OrderService(repository, paymentClient);
        Order order = service.createOrder("cust-1", new BigDecimal("10.00"));

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        Mockito.verify(repository).save(captor.capture());
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PAID);
        assertThat(captor.getValue().getCustomerId()).isEqualTo("cust-1");
    }

    @Test
    void getOrderThrowsWhenMissing() {
        OrderRepository repository = Mockito.mock(OrderRepository.class);
        PaymentClient paymentClient = Mockito.mock(PaymentClient.class);
        Mockito.when(repository.findById(42L)).thenReturn(Optional.empty());

        OrderService service = new OrderService(repository, paymentClient);

        assertThatThrownBy(() -> service.getOrder(42L))
            .isInstanceOf(OrderNotFoundException.class);
    }
}
