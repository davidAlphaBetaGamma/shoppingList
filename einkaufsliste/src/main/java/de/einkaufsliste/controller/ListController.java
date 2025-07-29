package de.einkaufsliste.controller;

import de.einkaufsliste.model.ListEntry;
import de.einkaufsliste.service.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
public class ListController {

    @Autowired
    private ListService listService;


    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ListEntry>> findAll() {
        log.info("controller method findAll() is called");
        return ResponseEntity.ok(listService.getAllItems());
    }


//    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ShoppingList> createShoppingList(@RequestBody ShoppingList newList) {
//        log.info("createShoppingList() is called.");
//        ShoppingList saved = listService.createList(newList);
//        return ResponseEntity.ok(saved);
//    }
//
//    @PostMapping(value = "/{listId}/item", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> addItemToList(@PathVariable Long listId, @RequestBody ListEntry listEntry) {
//        log.info("addItemToList is called.");
//        ListEntry saved = listService.addItemToList(listEntry, listId);
//
//        if (saved == null) {
//            log.debug("list is empty");
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(saved);
//    }
//
//
//    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ShoppingList>> getAllLists() {
//        Iterable<ShoppingList> IterLists = listService.getAllLists();
//        List<ShoppingList> lists = new ArrayList<>();
//
//        for (ShoppingList list : IterLists) {
//            lists.add(list);
//        }
//        return new ResponseEntity<>(lists, HttpStatus.OK);
//    }

}

