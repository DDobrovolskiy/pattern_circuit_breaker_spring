package org.example.config;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.bulkhead.configure.BulkheadConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BulkheadBeanConfig {
    @Bean
    public Bulkhead getBulkhead() {
        BulkheadConfig config =BulkheadConfig.custom().maxConcurrentCalls(1).build();
        BulkheadRegistry registry = BulkheadRegistry.of(config);
        return registry.bulkhead("my");
    }
}
