package org.example.controllers.request;

import org.example.proxy.IBookService;
import org.example.proxy.IBulkhead;
import org.example.proxy.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private IBookService bookService;
    @Autowired
    private IDataService dataService;
    @Autowired
    private IBulkhead bulkheadService;

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

    @GetMapping("/bulkhead")
    public String getBulkhead() throws InterruptedException, ExecutionException {
        Callable<String> callable = () -> bulkheadService.bulkhead("test");

        ForkJoinTask<String> task1 = ForkJoinPool.commonPool().submit(callable);
        ForkJoinTask<String> task2 = ForkJoinPool.commonPool().submit(callable);

        return task1.get() + task2.get();
    }
}
