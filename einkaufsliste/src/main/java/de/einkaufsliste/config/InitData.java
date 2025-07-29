package de.einkaufsliste.config;

import de.einkaufsliste.model.ListEntry;
import de.einkaufsliste.service.ListService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Component
public class InitData {

    private ListService listService;

    private DataSource dataSource;

    @PostConstruct
    public void init() {

        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("### DB URL: " + metaData.getURL());
            System.out.println("### User: " + metaData.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("### Initialized data ###");


        log.info("Create item 1");
        ListEntry item1 = new ListEntry();
        item1.setName("Karotte");
        item1.setQuantity(2);
        listService.addItem(item1);

        log.info("Create item 2");
        ListEntry item2 = new ListEntry();
        item2.setName("Kartoffeln");
        item2.setQuantity(3);
        listService.addItem(item2);

        log.info("Create item 3");
        ListEntry item3 = new ListEntry();
        item3.setName("Kuchen");
        item3.setQuantity(1);
        listService.addItem(item3);


//        log.info("Create Shopping List 1");
//        ShoppingList list = new ShoppingList();
//        list.setName("Einkauf");
//        listService.createList(list);
//
//        ListEntry newItem = new ListEntry();
//        newItem.setName("Banane");
//        newItem.setQuantity(3);
//        newItem.setShoppingList(list);
//        listService.addItemToList(newItem, list.getListId());

        log.info("### Data initialized ###");
    }


}
