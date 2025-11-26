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
    private String description;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return stepNumber == step.stepNumber && Objects.equals(id, step.id) && Objects.equals(description, step.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stepNumber, description);
    }
}
