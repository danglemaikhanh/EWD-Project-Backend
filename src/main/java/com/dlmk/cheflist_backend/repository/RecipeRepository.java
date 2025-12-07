package com.dlmk.cheflist_backend.repository;

import com.dlmk.cheflist_backend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    void deleteRecipeById(Long id);
    Optional<Recipe> findRecipeById(Long id);
    Optional<Recipe> findRecipeByOwnerUsername(String owner);
}
