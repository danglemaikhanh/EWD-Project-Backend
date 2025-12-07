package com.dlmk.cheflist_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stepNumber;

    @Column(columnDefinition="TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipe_id",  nullable = false, referencedColumnName = "id")
    private Recipe recipe;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return stepNumber == step.stepNumber &&
                Objects.equals(id, step.id) &&
                Objects.equals(description, step.description) &&
                Objects.equals(recipe, step.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stepNumber, description, recipe);
    }
}
