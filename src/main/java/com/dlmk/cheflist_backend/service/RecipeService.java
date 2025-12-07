package com.dlmk.cheflist_backend.service;

import com.dlmk.cheflist_backend.exception.RecipeNotFoundException;
import com.dlmk.cheflist_backend.model.Recipe;
import com.dlmk.cheflist_backend.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) { this.recipeRepository = recipeRepository; }

    public List<Recipe> getAllRecipes() { return recipeRepository.findAll(); }

    public Recipe getRecipesById(Long id) {
        return recipeRepository.findRecipeById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe by this " + id + " was not found"));
    }

    public Recipe addNewRecipe(Recipe recipe) {
        recipe.setFavorite(false);
        recipe.validate();
        return recipeRepository.save(recipe);
    }

    @Transactional
    public Recipe updateRecipe(Recipe recipe) { return recipeRepository.save(recipe); }

    @Transactional
    public void deleteRecipe(long id) { recipeRepository.deleteRecipeById(id); }

    public void setFavoriteRecipe(Long id) {
        Recipe recipe = getRecipesById(id);
        recipe.setFavorite(true);
        recipeRepository.save(recipe);
    }
}
