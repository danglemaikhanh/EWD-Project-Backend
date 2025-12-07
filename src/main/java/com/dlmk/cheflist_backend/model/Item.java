package com.dlmk.cheflist_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String measure;

    @Builder.Default
    private Boolean checked = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name= "todoShoppingList_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private TodoShoppingList todoShoppingList;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(measure, item.measure) &&
                Objects.equals(checked, item.checked) &&
                Objects.equals(todoShoppingList, item.todoShoppingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, measure, checked, todoShoppingList);
    }
}
