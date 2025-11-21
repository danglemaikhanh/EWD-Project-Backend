package com.dlmk.cheflist_backend.repository;

import com.dlmk.cheflist_backend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    List<Recipe> id(Long id);
}
