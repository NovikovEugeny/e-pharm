package by.pharmsystem.recipeservice.controller;

import by.pharmsystem.recipeservice.entity.Recipe;
import by.pharmsystem.recipeservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/add-recipe")
    public void addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
    }

    @PutMapping("/update-medicament-quantity/{recipeId}/{quantity}/")
    public void updateMedicamentQuantity(@PathVariable long recipeId, @PathVariable int quantity) {
        recipeService.updateMedicamentQuantity(recipeId, quantity);
    }

    @PutMapping("/close-recipes")
    public void closeRecipe(@RequestBody List<Long> identifiers) {
        recipeService.closeRecipes(identifiers);
    }

    @PutMapping("/extend-recipe/{recipeId}/")
    public void extendRecipe(@PathVariable long recipeId) {
        recipeService.extendRecipe(recipeId);
    }

    @GetMapping("/get-client-recipes/{clientId}/")
    public Map<Long, Integer> getClientRecipe(@PathVariable long clientId) {
        return recipeService.getClientRecipes(clientId);
    }

    @GetMapping("/show-client-recipes/{clientId}/")
    public List<Recipe> showClientRecipes(@PathVariable long clientId) {
        return recipeService.showClientRecipes(clientId);
    }
}
