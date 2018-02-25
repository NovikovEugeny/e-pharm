package by.pharmsystem.recipeservice.service;

import by.pharmsystem.recipeservice.entity.Recipe;
import by.pharmsystem.recipeservice.repository.RecipeRepository;
import by.pharmsystem.recipeservice.service.exception.ConflictException;
import by.pharmsystem.recipeservice.service.util.ConstantStorage;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public void addRecipe(Recipe recipe) {
        long clientId = recipe.getClientId();
        long medicamentId = recipe.getMedicamentId();
        Recipe existingRecipe = recipeRepository.findByClientIdAndMedicamentId(clientId, medicamentId);

        if (existingRecipe != null) {
            throw new ConflictException();
        }

        long id = recipeRepository.count() + 1;
        recipe.setId(id);
        recipe.setEndDate(new DateTime().plusMonths(3).toDate());
        recipe.setStatus(ConstantStorage.STATUS_OPEN);
        recipeRepository.save(recipe);
    }

    @Override
    public void closeRecipe(long recipeId) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        recipe.setStatus(ConstantStorage.STATUS_CLOSED);
        recipeRepository.save(recipe);
    }

    @Override
    public void updateMedicamentQuantity(long recipeId, int quantity) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        recipe.setQuantity(quantity);
        recipe.setEndDate(new DateTime().plusMonths(3).toDate());
        recipe.setStatus(ConstantStorage.STATUS_OPEN);
        recipeRepository.save(recipe);
    }

    @Override
    public Recipe getClientRecipe(long clientId, long medicamentId) {
        return recipeRepository.findByClientIdAndMedicamentId(clientId, medicamentId);
    }

    @Override
    public List<Recipe> showClientRecipes(long clientId) {
        return recipeRepository.findByClientId(clientId);
    }
}
