package com.manven.orderservice.service;

import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.springframework.stereotype.Component;

@Component
public class PaymentClient {

    @Retry(name = "payment")
    @TimeLimiter(name = "payment")
    public CompletionStage<Boolean> authorize(String customerId, BigDecimal amount) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(Duration.ofMillis(150));
            return true;
        });
    }

    private void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
