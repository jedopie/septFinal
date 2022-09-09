package com.example.septfinal.controller;

import com.example.septfinal.dao.ItemDAO;
import com.example.septfinal.model.Item;
import com.example.septfinal.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemDAO itemDAO;

    @GetMapping("/item")
    public Items getAllItems() {
        return itemDAO.getAllItems();
    }

    @GetMapping("item/{id}")
    public Item getItemById(@PathVariable(value="id") int id) {
        return itemDAO.getItemById(id);
    }

    @PostMapping(path= "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addItem(
            @RequestHeader(name = "X-COM-PERSIST", required = false) String headerPersist,
            @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
            @RequestBody Item item)
            throws Exception
    {
        Integer id = itemDAO.getAllItems().getItemsList().size() + 1;
        item.setId(String.valueOf(id));

        itemDAO.addItem(item);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(item.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(path= "/item", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateItem(
            @RequestHeader(name = "X-COM-PERSIST", required = false) String headerPersist,
            @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
            @RequestBody Item item)
            throws Exception
    {
        Item existingItem = itemDAO.getItemById(Integer.parseInt(item.getId()));
        existingItem.setName(item.getName());
        existingItem.setDesc(item.getDesc());
        existingItem.setPrice(item.getPrice());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(item.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path= "/item", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> deleteItem(
            @RequestHeader(name = "X-COM-PERSIST", required = false) String headerPersist,
            @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
            @RequestBody Item item)
            throws Exception
    {
        Items list = itemDAO.getAllItems();
        list.deleteByIndex(Integer.valueOf(item.getId()) -1);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(item.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }







}
