package org.example.controllers.request;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.proxy.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private BookService bookService;

    @GetMapping()
    public List<String> getInfo() {
        List<String> result = new LinkedList<>();
        for (int i = 0; i < 1; i++) {
            result.add(i + ":" + bookService.getBooks());
        }
        return result;
    }
}
