package org.example.proxy.impl;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import org.example.proxy.IRateLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class RateLimiterServiceImpl implements IRateLimiterService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TimeLimiter timeLimiter;

    @Override
    public String getTime(String value) {
        System.out.println("Use timeLimiter");
        String response = "null";
        var future = Executors.newFixedThreadPool(1).submit(() -> {
            System.out.println("Use REST" + value);
            return restTemplate.getForObject("http://localhost:8080/response" + value, String.class);
        });

        Callable<String> callable = TimeLimiter.decorateFutureSupplier(timeLimiter, () -> future);

        try {
            response = callable.call();
        } catch (Exception ignore) {    //Only test

        }
        return response;
    }
}
