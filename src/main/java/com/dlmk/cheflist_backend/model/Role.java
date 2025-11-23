package com.dlmk.cheflist_backend.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String role) {
        this.name = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(this.name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
