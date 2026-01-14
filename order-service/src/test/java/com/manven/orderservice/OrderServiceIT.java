package com.manven.orderservice;

import com.manven.orderservice.api.OrderRequest;
import com.manven.orderservice.api.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class OrderServiceIT {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
        .withDatabaseName("orders")
        .withUsername("orders_user")
        .withPassword("orders_pass");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createsAndFetchesOrder() {
        OrderRequest request = new OrderRequest("cust-42", new BigDecimal("25.00"));
        ResponseEntity<OrderResponse> createResponse = restTemplate.postForEntity("/orders", request, OrderResponse.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        OrderResponse created = createResponse.getBody();
        assertThat(created).isNotNull();
        assertThat(created.customerId()).isEqualTo("cust-42");

        ResponseEntity<OrderResponse> getResponse = restTemplate.getForEntity("/orders/" + created.id(), OrderResponse.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<List<OrderResponse>> listResponse = restTemplate.exchange(
            "/orders?customerId=cust-42",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            new ParameterizedTypeReference<>() {}
        );
        assertThat(listResponse.getBody()).isNotEmpty();
    }
}
