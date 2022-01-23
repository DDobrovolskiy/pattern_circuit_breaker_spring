package org.example.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircusBreakerBeanConfig {
    @Bean
    public CircuitBreaker countBase () {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED) // Скользящее окно автоматического выключателя COUNT_BASED будет учитывать количество вызовов удаленного сервиса
                .slidingWindowSize(10) //эта настройка помогает определить количество вызовов, которые следует учитывать при включении автоматического выключателя
                .slowCallRateThreshold(65F) //настраивает порог частоты отказов в процентах. Если x процентов вызовов не работают, выключатель отключается
                .slowCallDurationThreshold(Duration.ofSeconds(3L)) //настраивает порог продолжительности времени, при котором вызовы считаются медленными.
                .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);

        return registry.circuitBreaker("CIR_COUNT_BASED");
    }
}
