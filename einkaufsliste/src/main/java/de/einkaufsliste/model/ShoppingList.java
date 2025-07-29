//package de.einkaufsliste.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@NoArgsConstructor
//@Data
//@Entity
//public class ShoppingList {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long listId;
//
//    private String name;
//
//    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ListEntry> items = new ArrayList<>();
//}
