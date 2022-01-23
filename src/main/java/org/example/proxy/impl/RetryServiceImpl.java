package org.example.proxy.impl;

import io.github.resilience4j.retry.Retry;
import org.example.proxy.IRetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

/*
    <dependency>
      <groupId>io.github.resilience4j</groupId>
      <artifactId>resilience4j-retry</artifactId>
      <version>1.7.1</version>
    </dependency>

    автоматически повторить неудачный вызов с помощью Retry API
*/

@Component
public class RetryServiceImpl implements IRetryService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Retry retry;

    @Override
    public String retry(String value) {
        System.out.println("Use retry");
        String response = "null";
        Function<String, String> function = Retry.decorateFunction(
                retry, path -> {
                    System.out.println("Use REST");
                    return restTemplate.getForObject("http://localhost:8080/random" + path, String.class);
                });
        try {
            response = function.apply(value);
        } catch (Exception ignore) {    //Only test

        }
        return response;
        /*
        Use retry
        Use REST
        Use REST
        Use REST
         */
    }
}
