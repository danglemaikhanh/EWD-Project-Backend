package com.dlmk.cheflist_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @OneToMany(mappedBy = "recipe",  cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "recipe",   cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("stepNumber ASC")
    private List<Step> steps;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="owner_id", nullable=false)
    @JsonIgnore
    private AppUser owner;

    public void validate() throws IllegalStateException {
        if (ingredients.isEmpty()) {
            throw new IllegalStateException(
                    "You need at least one ingredient for your recipe!");
        } else if (steps.isEmpty()) {
            throw new IllegalStateException(
                    "You need at least one step for your recipe!");
        }
    }

    public void addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredient.setRecipe(null);
        ingredients.remove(ingredient);
    }

    public void addStep(Step step) {
        step.setRecipe(this);
        steps.add(step);
    }

    public void removeStep(Step step) {
        step.setRecipe(null);
        steps.remove(step);
    }

    // readOnly for API-Answer
    @Transient
    @JsonProperty
    public String getOwnerUsername() {
        return owner != null ? owner.getUsername() : "";
    }

}
