package by.pharmsystem.medicamentservice.repository;

import by.pharmsystem.medicamentservice.entity.Medicament;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepository extends MongoRepository<Medicament, Long> {
}
