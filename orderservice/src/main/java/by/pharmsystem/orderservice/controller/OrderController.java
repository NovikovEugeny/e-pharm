package by.pharmsystem.orderservice.controller;

import by.pharmsystem.orderservice.entity.Order;
import by.pharmsystem.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/confirm-order")
    public void confirmOrder(@RequestBody Map<String, Long> data) {
        orderService.confirmOrder(data);
    }

    @GetMapping("/show-orders")
    public List<Order> showOrders() {
        return orderService.showOrders();
    }

    @GetMapping("/show-confirmed-client-orders/{clientId}/")
    public List<Order> showConfirmedClientOrders(@PathVariable long clientId) {
        return orderService.showClientOrders(clientId);
    }

    @GetMapping("/show-unconfirmed-client-orders/{clientId}/")
    public List<Order> showUnconfirmedClientOrders(@PathVariable long clientId) {
        return orderService.showUnconfirmedClientOrders(clientId);
    }
}
