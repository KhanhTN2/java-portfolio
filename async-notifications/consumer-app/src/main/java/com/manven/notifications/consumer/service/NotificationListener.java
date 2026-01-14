package com.manven.notifications.consumer.service;

import com.manven.notifications.consumer.config.RabbitConfig;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {
    private static final Logger logger = LoggerFactory.getLogger(NotificationListener.class);
    private final IdempotencyService idempotencyService;

    public NotificationListener(IdempotencyService idempotencyService) {
        this.idempotencyService = idempotencyService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handleMessage(Map<String, Object> payload) {
        String key = payload.getOrDefault("recipient", "unknown") + ":" + payload.getOrDefault("message", "");
        if (!idempotencyService.shouldProcess(key)) {
            logger.info("Duplicate notification ignored: {}", key);
            return;
        }
        logger.info("Processing notification: {}", payload);
    }
}
