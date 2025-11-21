package com.dlmk.cheflist_backend.controller;

import com.dlmk.cheflist_backend.model.Recipe;
import com.dlmk.cheflist_backend.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }
    @GetMapping("{id}")
    public Recipe getRecipeById(@PathVariable Long id){
        return recipeService.getRecipesById(id);
    }
    @PostMapping(consumes = "application/json")
    public void addNewRecipe(@RequestBody Recipe recipe) {
        recipeService.insertRecipe(recipe);
    }
}
