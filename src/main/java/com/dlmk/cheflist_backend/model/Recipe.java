package com.dlmk.cheflist_backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String imageUrl;
    private String category;
    private String area;
    private boolean favorite = false;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedAt;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private List<Ingredient> ingredients;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "recipe_steps", joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id",  nullable = false),
        inverseJoinColumns = @JoinColumn(name = "step_id", referencedColumnName = "id"))
    private List<Step> steps;

    public void validate() throws IllegalStateException {
        if (ingredients.isEmpty()) {
            throw new IllegalStateException(
                    "You need at least one ingredient for your recipe!");
        } else if (steps.isEmpty()) {
            throw new IllegalStateException(
                    "You need at least one step for your recipe!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return favorite == recipe.favorite &&
                Objects.equals(id, recipe.id) &&
                Objects.equals(name, recipe.name) &&
                Objects.equals(imageUrl, recipe.imageUrl) &&
                Objects.equals(category, recipe.category) &&
                Objects.equals(ingredients, recipe.ingredients) &&
                Objects.equals(steps, recipe.steps) &&
                Objects.equals(area, recipe.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageUrl, category, area, favorite, ingredients, steps);
    }
}
