package by.pharmsystem.orderservice.service;

import by.pharmsystem.orderservice.entity.Order;

import java.util.List;

public interface OrderService {

    void createOrder(Order order);

    void confirmOrder();

    void cancelOrder(long orderId);

    List<Order> showOrders();

    List<Order> showClientOrders(long clientId);

    List<Order> showUnconfirmedClientOrders(long clientId);
}
