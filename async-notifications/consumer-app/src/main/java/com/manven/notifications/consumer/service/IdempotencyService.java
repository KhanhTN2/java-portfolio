package com.manven.notifications.consumer.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class IdempotencyService {
    private static final Duration TTL = Duration.ofMinutes(10);
    private final Map<String, Instant> processed = new ConcurrentHashMap<>();

    public boolean shouldProcess(String key) {
        Instant now = Instant.now();
        Instant existing = processed.putIfAbsent(key, now);
        return existing == null || existing.isBefore(now.minus(TTL));
    }

    @Scheduled(fixedDelayString = "PT5M")
    void cleanup() {
        Instant cutoff = Instant.now().minus(TTL);
        processed.entrySet().removeIf(entry -> entry.getValue().isBefore(cutoff));
    }
}
