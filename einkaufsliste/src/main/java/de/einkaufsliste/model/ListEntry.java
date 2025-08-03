package de.einkaufsliste.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "todos")
@Entity
@NoArgsConstructor
public class ListEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "todoId")
    private long listEntryId;

    @Column
    private String name;

    @Column
    private int quantity;

    @Column
    private boolean completed;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "list_id")
    @JsonBackReference
    private ShoppingList shoppingList;


}
