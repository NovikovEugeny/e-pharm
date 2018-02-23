package by.pharmsystem.orderservice.service;

import by.pharmsystem.orderservice.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void createOrder(Order order);

    void cancelOrder(long orderId);

    void confirmOrder(Map<String, Long> data);

    List<Order> showOrders();

    List<Order> showClientOrders(long clientId);

    List<Order> showUnconfirmedClientOrders(long clientId);
}
