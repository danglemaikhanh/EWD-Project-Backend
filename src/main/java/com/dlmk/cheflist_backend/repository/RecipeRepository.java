package com.dlmk.cheflist_backend.repository;

import com.dlmk.cheflist_backend.model.AppUser;
import com.dlmk.cheflist_backend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    void deleteRecipeById(Long id);
    Optional<Recipe> findRecipeById(Long id);
    Optional<Recipe> findRecipeByOwner(AppUser owner);

    @Query("select r.owner.username from Recipe r where r.id = :id")
    Optional<String> findOwnerUserNameById(Long id);
}
