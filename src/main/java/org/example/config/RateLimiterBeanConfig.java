package org.example.config;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimiterBeanConfig {
    @Bean
    public TimeLimiter getRateLimiter() {
        long ttl = 1000L;
        TimeLimiterConfig config
                = TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(ttl)).build();
        return TimeLimiter.of(config);
    }
}
