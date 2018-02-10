package by.pharmsystem.userservice.repository;

import by.pharmsystem.userservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    User findByLogin(String login);

    void deleteByLogin(String login);
}
