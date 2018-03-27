package by.pharmsystem.recipeservice.service;

import by.pharmsystem.recipeservice.entity.Recipe;

import java.util.List;
import java.util.Map;

public interface RecipeService {

    void addRecipe(Recipe recipe);

    void updateMedicamentQuantity(long recipeId, int quantity);

    void closeRecipes(List<Long> identifiers);

    void extendRecipe(long recipeId);

    Map<Long, Integer> getClientRecipes(long clientId);

    List<Recipe> showClientRecipes(long clientId);
}
