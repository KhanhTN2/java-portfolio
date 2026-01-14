package com.manven.notifications.producer.api;

import jakarta.validation.constraints.NotBlank;

public record NotificationRequest(
    @NotBlank String recipient,
    @NotBlank String message
) {
}
