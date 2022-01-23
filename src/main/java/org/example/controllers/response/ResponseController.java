package org.example.controllers.response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class ResponseController {
    @GetMapping("/sleep2000")
    public String getSleep2000() throws InterruptedException {
        Thread.sleep(2000L);
        return "test";
    }

    @GetMapping("/sleep500")
    public String getSleep500() throws InterruptedException {
        Thread.sleep(500L);
        return "test";
    }
}
