package com.dlmk.cheflist_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TodoShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    private double quantity;
    private String unit;

    private boolean checked;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TodoShoppingList that = (TodoShoppingList) o;
        return Double.compare(quantity, that.quantity) == 0
                && checked == that.checked && Objects.equals(id, that.id)
                && Objects.equals(ingredient, that.ingredient)
                && Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredient, quantity, unit, checked);
    }
}
