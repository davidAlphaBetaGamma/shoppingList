package de.einkaufsliste.service;

import de.einkaufsliste.model.ListEntry;
import de.einkaufsliste.model.ShoppingList;
import de.einkaufsliste.repository.ListEntryRepository;
import de.einkaufsliste.repository.ListRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ListService {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private ListEntryRepository listEntryRepository;


    // Creating a list
    public ShoppingList createList(ShoppingList newList) {
        log.info("createList() is called");
        return listRepository.save(newList);
    }

    public void itemCompleted(long itemId, boolean completed) {
        log.info("itemCompleted() is called");
//        ShoppingList list = listRepository.findById(listId).orElseThrow(() -> new RuntimeException("List not found"));

        ListEntry item = listEntryRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));

        item.setCompleted(completed);
    }

    // Item einer bestimmten Liste hinzufügen
    public ListEntry addItemToList(ListEntry newItem, long listId) {
        log.info("addItemToList() is called");
        ShoppingList list = listRepository.findById(listId).orElseThrow(() -> new RuntimeException("List not found"));

        newItem.setShoppingList(list);
        return listEntryRepository.save(newItem);
    }

    // Eine Liste mit ihrer ID laden
    public Optional<ShoppingList> getShoppingListById(long listId) {
        log.info("getShoppingList() is called");
        return listRepository.findById(listId);
    }

    // Item aus einer Liste löschen
    public void removeItemFromList(long itemId) {
        log.info("removeItem() is called");
        listEntryRepository.deleteById(itemId);
    }

    // Alle Listen zurückgeben
    public Iterable<ShoppingList> getAllLists() {
        log.info("getAllLists() is called");
        return listRepository.findAll();
    }

    // Liste löschen
    @Transactional
    public void deleteList(long listId) {
        log.info("deleteList() is called.");
        ShoppingList list = listRepository.findById(listId).orElseThrow(() -> new RuntimeException("List not found"));

        // Now delete the list
        listRepository.delete(list);
    }

    public Iterable<ListEntry> getAllItemsByListId(long listId) {
        log.info("getAllItemsByListId() is called");

        Optional<ShoppingList> list = listRepository.findById(listId);

        if (list.isEmpty()) {
            throw new RuntimeException("Items not found");
        }

        return list.get().getItems();
    }

    public ListEntry updateItem(long itemId, ListEntry updated) {
        ListEntry item = listEntryRepository.findById(itemId).orElse(null);
        if (item != null) {
            item.setName(updated.getName());
            item.setQuantity(updated.getQuantity());
            item.setCompleted(updated.isCompleted());
            listEntryRepository.save(item);
        }
        return item;
    }

}
