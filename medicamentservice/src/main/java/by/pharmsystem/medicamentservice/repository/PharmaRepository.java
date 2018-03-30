package by.pharmsystem.medicamentservice.repository;

import by.pharmsystem.medicamentservice.entity.Pharma;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmaRepository extends MongoRepository<Pharma, String> {
}
