package com.dlmk.cheflist_backend.service;

import com.dlmk.cheflist_backend.model.Recipe;
import com.dlmk.cheflist_backend.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipesById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "not found"));
    }

    public void insertRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }
}
