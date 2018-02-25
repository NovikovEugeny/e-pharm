package by.pharmsystem.recipeservice.repository;

import by.pharmsystem.recipeservice.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, Long> {

    Recipe findByClientIdAndMedicamentId(long clientId, long medicamentId);

    List<Recipe> findByClientId(long clientId);
}
