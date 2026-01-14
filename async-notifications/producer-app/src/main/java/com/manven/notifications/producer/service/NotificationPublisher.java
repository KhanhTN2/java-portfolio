package com.manven.notifications.producer.service;

import com.manven.notifications.producer.api.NotificationRequest;
import com.manven.notifications.producer.config.RabbitConfig;
import java.time.Instant;
import java.util.Map;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationPublisher {
    private final RabbitTemplate rabbitTemplate;

    public NotificationPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(NotificationRequest request) {
        Map<String, Object> payload = Map.of(
            "recipient", request.recipient(),
            "message", request.message(),
            "publishedAt", Instant.now().toString()
        );
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, payload);
    }
}
