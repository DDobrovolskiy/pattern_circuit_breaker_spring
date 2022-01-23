package org.example.config;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryBeanConfig {
    @Bean
    public Retry getRetry() {
        RetryConfig config = RetryConfig.custom().maxAttempts(3).build();
        RetryRegistry registry = RetryRegistry.of(config);
        return registry.retry("my");
    }
}
