package de.einkaufsliste.model;

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
    private boolean completed = false;

//    @ManyToOne
//    @JoinColumn(name = "list_id")
//    private ShoppingList shoppingList;
}
