package by.pharmsystem.orderservice.controller;

import by.pharmsystem.orderservice.entity.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PostMapping("/create-order")
    public void createOrder(@RequestBody Order order) {

    }

}
