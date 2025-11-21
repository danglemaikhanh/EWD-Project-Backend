package com.dlmk.cheflist_backend.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double quantity;
    private String unit;

    public Ingredient() {
    }

    public Ingredient(Long id, String name, double quantity, String unit) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.quantity = quantity;
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity cannot be negative");
        }
        this.unit = Objects.requireNonNull(unit);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Double.compare(quantity, that.quantity) == 0 &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, unit);
    }
}
