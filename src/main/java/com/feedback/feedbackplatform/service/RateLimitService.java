package com.feedback.feedbackplatform.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {

    private static final int MAX_REQUESTS = 100;
    private static final long WINDOW_MS = 60_000;

    private final ConcurrentHashMap<String, Counter> clients = new ConcurrentHashMap<>();

    public boolean allowRequest(String key) {
        long now = Instant.now().toEpochMilli();

        clients.putIfAbsent(key, new Counter(0, now));
        Counter counter = clients.get(key);

        synchronized (counter) {
            if (now - counter.startTime > WINDOW_MS) {
                counter.count = 0;
                counter.startTime = now;
            }
            counter.count++;
            return counter.count <= MAX_REQUESTS;
        }
    }

    private static class Counter {
        int count;
        long startTime;

        Counter(int count, long startTime) {
            this.count = count;
            this.startTime = startTime;
        }
    }
}
