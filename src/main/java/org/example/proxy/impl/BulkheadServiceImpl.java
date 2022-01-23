package org.example.proxy.impl;

import io.github.resilience4j.bulkhead.Bulkhead;
import org.example.proxy.IBulkheadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

/*
Ограничить количество одновременных обращений к определенной службе.

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-bulkhead</artifactId>
    <version>1.7.1</version>
</dependency>
*/

@Component
public class BulkheadServiceImpl implements IBulkheadService {
    @Autowired
    private Bulkhead bulkhead;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String bulkhead(String value) {
        System.out.println("Use bulkhead");
        String response = "null";
        Function<String, String> function = Bulkhead.decorateFunction(
                bulkhead, path -> {
                    System.out.println("Use REST");
                    return restTemplate.getForObject("http://localhost:8080/random" + path, String.class);
                });
        try {
            response = function.apply(value);
        } catch (Exception ignore) {    //Only test

        }
        return response;

        /*
        Use bulkhead
        Use REST
        Use bulkhead
        */
    }
}
