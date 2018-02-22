package by.pharmsystem.orderservice.service;

import by.pharmsystem.orderservice.entity.Order;
import by.pharmsystem.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        //TODO: validate order

        long id = orderRepository.count() + 1;
        order.setId(id);
        orderRepository.save(order);

        //TODO: get money
    }

    @Override
    public void cancelOrder(long id) {
        orderRepository.delete(id);

        //TODO: return money
    }

    @Override
    @Transactional
    public void confirmOrder() {

    }

    @Override
    public List<Order> showOrders() {
        return orderRepository.findByStatus("new");
    }

    @Override
    public List<Order> showClientOrders(long clientId) {
        return orderRepository.findByClientIdAndStatus(clientId, "sent");
    }

    @Override
    public List<Order> showUnconfirmedClientOrders(long clientId) {
        return orderRepository.findByClientIdAndStatus(clientId, "new");
    }
}
