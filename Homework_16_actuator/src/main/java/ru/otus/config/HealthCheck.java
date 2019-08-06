package ru.otus.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class HealthCheck implements HealthIndicator {

    private final Random random = new Random();

    @Override
    public Health health() {
        if (!isRunning()) {
            return Health.down().withDetail("Library", "Not Available").build();
        }
        return Health.up().withDetail("Library", "Available").build();
    }
    private Boolean isRunning() {
        return random.nextBoolean();
    }
}