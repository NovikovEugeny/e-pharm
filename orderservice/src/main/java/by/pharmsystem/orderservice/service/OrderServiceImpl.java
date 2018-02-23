package by.pharmsystem.orderservice.service;

import by.pharmsystem.orderservice.entity.Order;
import by.pharmsystem.orderservice.repository.OrderRepository;
import by.pharmsystem.orderservice.service.util.ConstantStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        //TODO: validate order

        //TODO: calculate cost

        List<Long> body = new ArrayList<>();
        body.add(1L);
        body.add(2L);
        body.add(3L);

        RequestEntity request = RequestEntity
                .post(new URI("http://localhost:8084/get"))
                .accept(MediaType.APPLICATION_JSON)
                .body(body);
        ParameterizedTypeReference<List<Double>> myBean = new ParameterizedTypeReference<List<Double>>() {};
        ResponseEntity<List<Double>> response = new RestTemplate().exchange(request, myBean);
        List<Double> dl = response.getBody();
        System.out.println(dl);


        long id = orderRepository.count() + 1;
        order.setId(id);
        //order.setCost(cost);
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(long orderId) {
        orderRepository.delete(orderId);
    }

    @Override
    public void confirmOrder(Map<String, Long> data) {
        long orderId = data.get(ConstantStorage.ORDER_ID);
        long pharmacistId = data.get(ConstantStorage.PHARMACIST_ID);

        Order order = orderRepository.findOne(orderId);
        order.setPharmacistId(pharmacistId);
        order.setStatus(ConstantStorage.STATUS_SENT);
        orderRepository.save(order);

        //TODO: send message on email
    }

    @Override
    public List<Order> showOrders() {
        return orderRepository.findByStatus(ConstantStorage.STATUS_NEW);
    }

    @Override
    public List<Order> showClientOrders(long clientId) {
        return orderRepository.findByClientIdAndStatus(clientId, ConstantStorage.STATUS_SENT);
    }

    @Override
    public List<Order> showUnconfirmedClientOrders(long clientId) {
        return orderRepository.findByClientIdAndStatus(clientId, ConstantStorage.STATUS_NEW);
    }
}
