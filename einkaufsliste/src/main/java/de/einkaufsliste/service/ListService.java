package de.einkaufsliste.service;

import de.einkaufsliste.model.ListEntry;
import de.einkaufsliste.repository.ListEntryRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ListService {

//    @Autowired
//    private ListRepository listRepository;

    @Autowired
    private ListEntryRepository listEntryRepository;


    // Create a listEntry
    public ListEntry addItem(ListEntry item) {
        log.info("addItem() is called.");
        return listEntryRepository.save(item);
    }

    // Delete item
    public void deleteItem(long id) {
        log.info("deleteItem() is called.");
        listEntryRepository.deleteById(id);
    }

    // Get item by id
    public ListEntry getItemById(long id) {
        log.info("getItemById() is called.");
        return listEntryRepository.findById(id).get();
    }

    // Get all items
    public Iterable<ListEntry> getAllItems() {
        log.info("getAllItems() is called.");
        return listEntryRepository.findAll();
    }

    // Update status if completed
    public ListEntry itemCompleted(long id) {
        log.info("itemCompleted() is called.");
        ListEntry item = getItemById(id);
        item.setCompleted(true);

        return item;
    }

//    // Creating a list
//    public ShoppingList createList(ShoppingList newList) {
//        log.info("createList() is called");
//        return listRepository.save(newList);
//    }
//
//    // Item einer bestimmten Liste hinzufügen
//    public ListEntry addItemToList(ListEntry newItem, long listId) {
//        log.info("addItemToList() is called");
//        ShoppingList list = listRepository.findById(listId).orElseThrow(() -> new RuntimeException("List not found"));
//
//        newItem.setShoppingList(list);
//        return listEntryRepository.save(newItem);
//    }
//
//    // Eine Liste mit ihrer ID laden
//    public Optional<ShoppingList> getShoppingListById(long listId) {
//        log.info("getShoppingList() is called");
//        return listRepository.findById(listId);
//    }
//
//    // Item aus einer Liste löschen
//    public void removeItem(long listEntryId) {
//        log.info("removeItem() is called");
//        listEntryRepository.deleteById(listEntryId);
//    }
//
//    // Alle Listen zurückgeben
//    public Iterable<ShoppingList> getAllLists() {
//        log.info("getAllLists() is called");
//        return listRepository.findAll();
//    }
//
//    // Liste löschen
//    public void deleteList(long listId) {
//        log.info("deleteList() is called.");
//        listRepository.deleteById(listId);
//    }

}
