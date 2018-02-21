package by.pharmsystem.medicamentservice.repository;

import by.pharmsystem.medicamentservice.entity.Medicament;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentRepository extends MongoRepository<Medicament, Long> {

    List<Medicament> findByGroup(String group);

    List<Medicament> findByNameIgnoreCase(String name);
}
