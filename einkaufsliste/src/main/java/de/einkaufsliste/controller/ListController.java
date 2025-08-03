package de.einkaufsliste.controller;

import de.einkaufsliste.model.ListEntry;
import de.einkaufsliste.model.ShoppingList;
import de.einkaufsliste.repository.ListEntryRepository;
import de.einkaufsliste.service.ListService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
public class ListController {

    @Autowired
    private ListService listService;

    @Autowired
    private ListEntryRepository repo;

    @PostMapping(value = "/lists", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingList> createShoppingList(@RequestBody ShoppingList newList) {
        log.info("createShoppingList() is called.");
        ShoppingList saved = listService.createList(newList);
        return ResponseEntity.ok(saved);
    }

    @PostMapping(value = "/lists/{listId}/items", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addItemToList(@PathVariable Long listId, @RequestBody ListEntry listEntry) {
        log.info("addItemToList is called.");
        ListEntry saved = listService.addItemToList(listEntry, listId);

        if (saved == null) {
            log.debug("list is empty");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(saved);
    }


    @GetMapping(value = "/lists", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShoppingList>> getAllLists() {
        log.info("getAllLists() is called by controller");
        return ResponseEntity.ok(StreamSupport.stream(listService.getAllLists().spliterator(), false).collect(Collectors.toList()));
    }

    @DeleteMapping(value = "/lists/{listId}")
    public ResponseEntity<Void> deleteList(@PathVariable Long listId) {
        log.info("deleteList() is called");
        listService.deleteList(listId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/items/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        log.info("deleteItem() is called");
        listService.removeItemFromList(itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/lists/{listId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingList> getShoppingListById(@PathVariable Long listId) {
        log.info("getShoppingListById() is called by controller");
        ShoppingList list = listService.getShoppingListById(listId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(list);
    }

    @PatchMapping(value = "/items/{itemId}")
    public ResponseEntity<Void> markItemCompleted(@PathVariable Long itemId, Boolean completed) {
        log.info("isCompleted() is called by controller");
        listService.itemCompleted(itemId, completed);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/lists/{listId}/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ListEntry>> getAllItemsByListId(@PathVariable Long listId) { // @PathVariable("itemId") wird nur benutzt, wenn der Parametername nicht gleich der Pfadname ist
        log.info("getAllItemsByListId is called by controller");
        Iterable<ListEntry> items = listService.getAllItemsByListId(listId);
        return ResponseEntity.ok(items);
    }

    @PutMapping(value = "/items/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListEntry> updateItem(@PathVariable Long itemId, @RequestBody ListEntry updated) { // Long != long, denn "Long" ist eine Wrapper Klasse, d.h. Wert kann null sein
        log.info("updateItem() is called by controller");

        return ResponseEntity.ok(listService.updateItem(itemId, updated));
    }
}

