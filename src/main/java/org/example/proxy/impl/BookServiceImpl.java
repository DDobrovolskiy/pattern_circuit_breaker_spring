package org.example.proxy.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.proxy.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookServiceImpl implements IBookService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @CircuitBreaker(name = "bookService", fallbackMethod = "getBooksFail")
    public String getBooks() {
        return restTemplate.getForObject("http://localhost:8080/random", String.class);
    }

    public String getBooksFail(Exception e) {
        return "Books Service is Closed: " + e.toString();
    }
}
