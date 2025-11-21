package com.dlmk.cheflist_backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stepNumber;
    private String description;

    public Step() {
    }

    public Step(Long id, String description, int stepNumber) {
        this.id = id;
        this.description = description;
        this.stepNumber = stepNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

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
