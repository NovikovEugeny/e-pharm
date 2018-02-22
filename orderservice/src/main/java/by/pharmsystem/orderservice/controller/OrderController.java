package by.pharmsystem.orderservice.controller;

import by.pharmsystem.orderservice.entity.Order;
import by.pharmsystem.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public void createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
    }

    @DeleteMapping("/cancel-order/{orderId}/")
    public void cancelOrder(@PathVariable long orderId) {
        orderService.cancelOrder(orderId);
    }

    @PatchMapping("/confirm-order")
    public void confirmOrder() {
        orderService.confirmOrder();
    }

    @GetMapping("/show-orders")
    public List<Order> showOrders() {
        return orderService.showOrders();
    }

    @GetMapping("/show-unconfirmed-orders/{clientId}/")
    public List<Order> showUnconfirmedClientOrders(@PathVariable long clientId) {
        return orderService.showUnconfirmedClientOrders(clientId);
    }

    @GetMapping("/show-confirmed-orders/{clientId}/")
    public List<Order> showConfirmedClientOrders(@PathVariable long clientId) {
        return orderService.showClientOrders(clientId);
    }
}
