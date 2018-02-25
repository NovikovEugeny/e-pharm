package by.pharmsystem.orderservice.service;

import by.pharmsystem.orderservice.entity.Order;
import by.pharmsystem.orderservice.repository.OrderRepository;
import by.pharmsystem.orderservice.service.util.ConstantStorage;
import by.pharmsystem.orderservice.service.util.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        OrderValidator.validate(order);

        //get medicament identifiers
        List<Long> identifiers = new ArrayList<>();
        Map<Long, Integer> idQuantity = order.getMedicaments();
        idQuantity.forEach((k, v) -> identifiers.add(k));

        //get recipe requirements
        HttpEntity<List<Long>> recipeRequest = new HttpEntity<>(identifiers);
        ParameterizedTypeReference<Map<Long, Boolean>> type1 = new ParameterizedTypeReference<Map<Long, Boolean>>() {};
        ResponseEntity<Map<Long, Boolean>> response1 =
                new RestTemplate().exchange("http://localhost:8084/get-recipe-requirements", HttpMethod.POST, recipeRequest, type1);
        Map<Long, Boolean> idIsRecipe = response1.getBody();

        Map<Long, Long> clientMedicament = new HashMap<>();
        idIsRecipe.forEach((k, v) -> {
            if (v) {
                //check permission
            }
        });

        //get medicament prices
        HttpEntity<List<Long>> request = new HttpEntity<>(identifiers);
        ParameterizedTypeReference<Map<Long, Double>> type2 = new ParameterizedTypeReference<Map<Long, Double>>() {};
        ResponseEntity<Map<Long, Double>> response2 =
                new RestTemplate().exchange("http://localhost:8084/get-prices", HttpMethod.POST, request, type2);
        Map<Long, Double> idPrice = response2.getBody();

        final double[] cost = {0};
        idQuantity.forEach((k, v) -> cost[0] += v * idPrice.get(k));

        long id = orderRepository.count() + 1;
        order.setId(id);
        order.setCost(cost[0]);
        order.setRequestDate(new Date());
        order.setStatus(ConstantStorage.STATUS_NEW);
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
        order.setResponseDate(new Date());
        order.setStatus(ConstantStorage.STATUS_SENT);
        orderRepository.save(order);
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
