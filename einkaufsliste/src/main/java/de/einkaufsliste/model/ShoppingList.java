package de.einkaufsliste.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "shopping_list")
public class ShoppingList {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long listId;

    private String name;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ListEntry> items = new ArrayList<>();
}
