package by.pharmsystem.recipeservice.service;

import by.pharmsystem.recipeservice.entity.Recipe;
import by.pharmsystem.recipeservice.repository.RecipeRepository;
import by.pharmsystem.recipeservice.service.exception.ConflictException;
import by.pharmsystem.recipeservice.service.util.ConstantStorage;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void closeRecipes(List<Long> identifiers) {
        identifiers.forEach(recipeId -> {
            Recipe recipe = recipeRepository.findOne(recipeId);
            recipe.setStatus(ConstantStorage.STATUS_CLOSED);
            recipeRepository.save(recipe);
        });
    }

    @Override
    public void extendRecipe(long recipeId) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        recipe.setEndDate(new DateTime(recipe.getEndDate()).plusMonths(3).toDate());
        recipe.setStatus(ConstantStorage.STATUS_OPEN);
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
    public Map<Long, Integer> getClientRecipes(long clientId) {
        List<Recipe> recipes = recipeRepository.findByClientId(clientId);

        if (recipes == null) {
            return null;
        } else {
            return recipes.stream()
                    .filter(recipe -> recipe.getStatus().equals(ConstantStorage.STATUS_OPEN)
                            && recipe.getEndDate().after(new Date()))
                    .collect(Collectors.toMap(Recipe::getMedicamentId, Recipe::getQuantity));
        }
    }

    @Override
    public List<Recipe> showClientRecipes(long clientId) {
        return recipeRepository.findByClientId(clientId);
    }
}
