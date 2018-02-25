package by.pharmsystem.recipeservice.service;

import by.pharmsystem.recipeservice.entity.Recipe;

import java.util.List;
import java.util.Map;

public interface RecipeService {

    void addRecipe(Recipe recipe);

    void updateMedicamentQuantity(long recipeId, int quantity);

    void closeRecipe(long recipeId);

    Map<Long, Recipe> getClientRecipePermission(long clientId, long medicamentId);

    List<Recipe> showClientRecipes(long clientId);
}
