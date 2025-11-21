package com.dlmk.cheflist_backend.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipeName;
    private String imageUrl;
    private String category;
    private String area;

    //private List<IngredientQuantity> ingredients = new ArrayList<>();
    //private List<Step> steps = new ArrayList<>();

    private boolean favorite;

    public Recipe() {
    }

    public Recipe(Long id, String recipeName, String imageUrl, String category, String area, boolean favorite) {
        this.id = id;
        this.recipeName = recipeName;
        this.imageUrl = imageUrl;
        this.category = category;
        this.area = area;
        this.favorite = favorite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return favorite == recipe.favorite &&
                Objects.equals(id, recipe.id) &&
                Objects.equals(recipeName, recipe.recipeName) &&
                Objects.equals(imageUrl, recipe.imageUrl) &&
                Objects.equals(category, recipe.category) &&
                Objects.equals(area, recipe.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeName, imageUrl, category, area, favorite);
    }
}
