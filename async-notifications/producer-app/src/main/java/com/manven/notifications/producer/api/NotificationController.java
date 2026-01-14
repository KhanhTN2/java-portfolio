package com.manven.notifications.producer.api;

import com.manven.notifications.producer.service.NotificationPublisher;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationPublisher notificationPublisher;

    public NotificationController(NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, String> publish(@Valid @RequestBody NotificationRequest request) {
        notificationPublisher.publish(request);
        return Map.of("status", "queued");
    }
}
