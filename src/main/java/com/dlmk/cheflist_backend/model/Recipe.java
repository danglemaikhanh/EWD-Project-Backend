package com.dlmk.cheflist_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;
    private String name;
    private String imageUrl;
    private String category;
    private String area;
    private boolean favorite;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Ingredient> ingredients;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "recipe_steps", joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "step_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Step> steps;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return favorite == recipe.favorite &&
                Objects.equals(id, recipe.id) &&
                Objects.equals(name, recipe.name) &&
                Objects.equals(imageUrl, recipe.imageUrl) &&
                Objects.equals(category, recipe.category) &&
                Objects.equals(area, recipe.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageUrl, category, area, favorite);
    }
}
