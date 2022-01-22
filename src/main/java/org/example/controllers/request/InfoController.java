package org.example.controllers.request;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.example.proxy.BookService;
import org.example.proxy.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private BookService bookService;
    @Autowired
    private DataService dataService;

    @GetMapping("/book")
    public List<String> getBook() {
        List<String> result = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            result.add(i + ":" + bookService.getBooks());
        }
        return result;
    }

    @GetMapping("/data")
    public String getData() {
        return dataService.getData();
    }

    @GetMapping("/dataOf")
    public String getDataOf() {
        return dataService.getDateOf("test");
    }
}
