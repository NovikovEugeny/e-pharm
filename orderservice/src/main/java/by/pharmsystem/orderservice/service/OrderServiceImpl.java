package by.pharmsystem.orderservice.service;

import by.pharmsystem.orderservice.entity.Order;
import by.pharmsystem.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {

    }

    @Override
    public void cancelOrder(long id) {

    }

    @Override
    public List<Order> showOrders(long clientId) {
        return null;
    }

    @Override
    public List<Order> showUnconfirmedOrders(long clientId) {
        return null;
    }
}
