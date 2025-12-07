package com.dlmk.cheflist_backend.controller;

import com.dlmk.cheflist_backend.model.Recipe;
import com.dlmk.cheflist_backend.repository.AppUserRepository;
import com.dlmk.cheflist_backend.security.CustomUserDetails;
import com.dlmk.cheflist_backend.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final AppUserRepository appUserRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        List<Recipe> recipes = recipeService.getAllRecipes();
        return new ResponseEntity<>(recipes,HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<Optional<Recipe>> getMyRecipes(@AuthenticationPrincipal CustomUserDetails principal){
        Optional<Recipe> recipes = recipeService.getMyRecipes(principal.getUsername());
        return new ResponseEntity<>(recipes,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") Long id){
        Recipe recipe = recipeService.getRecipesById(id);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe, @AuthenticationPrincipal CustomUserDetails principal){
        var saved = recipeService.addNewRecipe(recipe, principal.getUsername());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe){
        var updated = recipeService.updateRecipe(id, recipe);
        return new ResponseEntity<>(updated, HttpStatus.OK);
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
