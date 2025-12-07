package com.dlmk.cheflist_backend.service;

import com.dlmk.cheflist_backend.exception.RecipeNotFoundException;
import com.dlmk.cheflist_backend.model.Recipe;
import com.dlmk.cheflist_backend.repository.AppUserRepository;
import com.dlmk.cheflist_backend.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final AppUserRepository appUserRepository;

    public List<Recipe> getAllRecipes() { return recipeRepository.findAll(); }

    public Recipe getRecipesById(Long id) {
        return recipeRepository.findRecipeById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe by this " + id + " was not found"));
    }

    public Recipe addNewRecipe(Recipe recipe, String username) {
        var owner = appUserRepository.findByUsername(username).orElseThrow();
        recipe.setId(null);
        recipe.setOwner(owner);
        recipe.setFavorite(false);
        recipe.validate();
        return recipeRepository.save(recipe);
    }

    public Optional<Recipe> getMyRecipes(String username) {
        return recipeRepository.findRecipeByOwner(appUserRepository.findByUsername(username).orElseThrow());
    }

    @PreAuthorize("hasRole('ADMIN') or @authz.isRecipeOwner(#id, authentication)")
    public Recipe updateRecipe(Long id, Recipe recipe) {
        var r = recipeRepository.findRecipeById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe by this " + id + " was not found"));
        r.setName(recipe.getName());
        r.setCategory(recipe.getCategory());
        r.setArea(recipe.getArea());
        r.setImageUrl(recipe.getImageUrl());
        // Ingredients sync later
        // Steps sync later
        return  recipeRepository.save(recipe);
    }

    @PreAuthorize("hasRole('ADMIN') or @authz.isRecipeOwner(#id, authentication)")
    public void deleteRecipe(long id) { recipeRepository.deleteRecipeById(id); }

    public void setFavoriteRecipe(Long id) {
        Recipe recipe = getRecipesById(id);
        recipe.setFavorite(true);
        recipeRepository.save(recipe);
    }
}
