package by.pharmsystem.orderservice.repository;

import by.pharmsystem.orderservice.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

    List<Order> findByStatus(String status);

    List<Order> findByClientIdAndStatus(long clientId, String status);
}
