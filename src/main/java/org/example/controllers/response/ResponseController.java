package org.example.controllers.response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class ResponseController {
    @GetMapping("/test")
    public String get() throws InterruptedException {
        Thread.sleep(6000L);
        return "test";
    }
}
