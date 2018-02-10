package by.pharmsystem.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TC {

    @GetMapping("/test")
    public String test() {
        return "Hello world!!!";
    }

    @GetMapping("/test-s")
    public String testw() {
        return "Hello world!!!";
    }

}
