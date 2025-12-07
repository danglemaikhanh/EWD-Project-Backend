package com.dlmk.cheflist_backend.controller;

import com.dlmk.cheflist_backend.model.Recipe;
import com.dlmk.cheflist_backend.repository.AppUserRepository;
import com.dlmk.cheflist_backend.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        List<Recipe> recipes = recipeService.getAllRecipes();
        return new ResponseEntity<>(recipes,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") Long id){
        Recipe recipe = recipeService.getRecipesById(id);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe){
        Recipe newRecipe = recipeService.addNewRecipe(recipe);
        return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe){
        Recipe updateRecipe = recipeService.updateRecipe(recipe);
        return new ResponseEntity<>(updateRecipe, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id){
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/favorite")
    public ResponseEntity<Recipe> markAsFavorite(@PathVariable("id") Long id){
        recipeService.setFavoriteRecipe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
