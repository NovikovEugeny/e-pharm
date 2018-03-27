package by.pharmsystem.orderservice.service;

import by.pharmsystem.orderservice.entity.Order;
import by.pharmsystem.orderservice.repository.OrderRepository;
import by.pharmsystem.orderservice.service.exception.ConflictException;
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
@SuppressWarnings("unchecked")
public class OrderServiceImpl implements OrderService {

    private static final String RECIPE_REQUIREMENTS_REQUEST = "http://localhost:8084/get-recipe-requirements";
    private static final String PRICE_REQUEST = "http://localhost:8084/get-prices";
    private static final String CLIENT_ACTIVE_RECIPE_REQUEST = "http://localhost:8085/get-client-recipes/";
    private static final String CLOSE_RECIPES_RQUEST = "http://localhost:8085/close-recipes";

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        //OrderValidator.validate(order);

        //medicament identifiers
        List<Long> identifiers = new ArrayList<>();
        Map<Long, Integer> idQuantity = order.getMedicaments();
        idQuantity.forEach((k, v) -> identifiers.add(k));

        //get recipe requirements (medicament id - isRecipe)
        HttpEntity<List<Long>> recipeRequirementsRequestBody = new HttpEntity<>(identifiers);
        ParameterizedTypeReference<Map<Long, Boolean>> longBooleanMap = new ParameterizedTypeReference<Map<Long, Boolean>>() {};
        ResponseEntity<Map<Long, Boolean>> recipeRequirementsResponse = new RestTemplate()
                .exchange(RECIPE_REQUIREMENTS_REQUEST, HttpMethod.POST, recipeRequirementsRequestBody, longBooleanMap);
        Map<Long, Boolean> idIsRecipe = recipeRequirementsResponse.getBody();

        System.out.println("id-is-recipe: " + idIsRecipe);

        final boolean[] hasRecipeRequirements = {false};
        idIsRecipe.forEach((k, v) -> {
            if (v) {
                hasRecipeRequirements[0] = true;
            }
        });

        //if any medicament from order require a recipe, does request for getting client recipes
        Map<Long, Integer> idQuantityInRecipe;
        if (hasRecipeRequirements[0]) {
            idQuantityInRecipe = new RestTemplate().getForObject(CLIENT_ACTIVE_RECIPE_REQUEST +
                    order.getClientId() + "/", Map.class);

            System.out.println("idQuantityInRecipe: " + idQuantityInRecipe);

            if (idQuantityInRecipe == null) {
                throw new ConflictException();
            }

            //check permissions
            idIsRecipe.forEach((k, v) -> {
                if (v) {
                    Integer permittedQuantity = idQuantityInRecipe.get(k);

                    System.out.println("id=" + k + ", permitted: " + permittedQuantity);

                    if (permittedQuantity == null || !permittedQuantity.equals(idQuantity.get(k))) {
                        System.out.println("this");
                        throw new ConflictException();
                    }
                }
            });
        }

        //get medicament prices
        HttpEntity<List<Long>> medicamentPricesRequestBody = new HttpEntity<>(identifiers);
        ParameterizedTypeReference<Map<Long, Double>> longDoubleMap = new ParameterizedTypeReference<Map<Long, Double>>() {};
        ResponseEntity<Map<Long, Double>> medicamentPricesResponse =
                new RestTemplate().exchange(PRICE_REQUEST, HttpMethod.POST, medicamentPricesRequestBody, longDoubleMap);
        Map<Long, Double> idPrice = medicamentPricesResponse.getBody();

        System.out.println("id - price: " + idPrice);

        final double[] cost = {0};
        idQuantity.forEach((k, v) -> cost[0] += v * idPrice.get(k));

        long id = orderRepository.count() + 1;
        order.setId(id);
        order.setCost(cost[0]);
        order.setRequestDate(new Date());
        order.setStatus(ConstantStorage.STATUS_NEW);
        orderRepository.save(order);

        //closing recipes
        List<Long> recipesToClosing = new ArrayList<>();
        idIsRecipe.forEach((k, v) -> {
            if (v) {
                recipesToClosing.add(k);
            }
        });

        if (recipesToClosing.size() > 0) {
            new RestTemplate().put(CLOSE_RECIPES_RQUEST, recipesToClosing);
        }
    }

    @Override
    public void cancelOrder(long orderId) {
        orderRepository.delete(orderId);

        //TODO: open recipes
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
