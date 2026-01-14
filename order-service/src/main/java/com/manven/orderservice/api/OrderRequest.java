package com.manven.orderservice.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record OrderRequest(
    @NotBlank String customerId,
    @NotNull @DecimalMin("0.01") BigDecimal amount
) {
}
