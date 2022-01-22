package org.example.proxy.impl;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.example.proxy.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class DataServiceImpl implements DataService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CircuitBreaker circuitBreaker;

    @Override
    public String getData() {
        System.out.println("Use getData");
        String response = "null";
        Supplier<String> supplier = CircuitBreaker.decorateSupplier(
                circuitBreaker,
                () -> {
                    System.out.println("Use REST");
                    return restTemplate.getForObject("http://localhost:8080/random", String.class);
                });
        try {
            response = supplier.get();
        } catch (Exception ignore) {    //Only test

        }
        return response;
    }

    @Override
    public String getDateOf(String value) {
        System.out.println("Use getDataOf");
        String response = "null";
        Function<String, String> function = CircuitBreaker.decorateFunction(
                circuitBreaker, path -> {
                    System.out.println("Use REST");
                    return restTemplate.getForObject("http://localhost:8080/random" + path, String.class);
                });
        try {
            response = function.apply(value);
        } catch (Exception ignore) {    //Only test

        }
        return response;
    }
}
