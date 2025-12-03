package com.dlmk.cheflist_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String measure;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(measure, that.measure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, measure);
    }
}
