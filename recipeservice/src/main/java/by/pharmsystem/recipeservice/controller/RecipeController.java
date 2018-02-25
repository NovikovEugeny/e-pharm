package by.pharmsystem.recipeservice.controller;

import by.pharmsystem.recipeservice.entity.Recipe;
import by.pharmsystem.recipeservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/add-recipe")
    public void addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
    }

    @PatchMapping("/update-medicament-quantity/{recipeId}/{quantity}/")
    public void updateMedicamentQuantity(@PathVariable long recipeId, @PathVariable int quantity) {
        recipeService.updateMedicamentQuantity(recipeId, quantity);
    }

    @PatchMapping("/close-recipe/{recipeId}/")
    public void closeRecipe(@PathVariable long recipeId) {
        recipeService.closeRecipe(recipeId);
    }

    @GetMapping("/get-client-recipe/{clientId}/{medicamentId}/}")
    public Recipe getClientRecipe(@PathVariable long clientId, @PathVariable long medicamentId) {
        return recipeService.getClientRecipe(clientId, medicamentId);
    }

    @GetMapping("/show-client-recipes/{clientId}/")
    public List<Recipe> showClientRecipes(@PathVariable long clientId) {
        return recipeService.showClientRecipes(clientId);
    }
}
