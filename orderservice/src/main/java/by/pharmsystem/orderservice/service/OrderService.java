package by.pharmsystem.orderservice.service;

import by.pharmsystem.orderservice.entity.Order;

import java.util.List;

public interface OrderService {

    void createOrder(Order order);

    void cancelOrder(long id);

    List<Order> showOrders(long clientId);

    List<Order> showUnconfirmedOrders(long clientId);
}
