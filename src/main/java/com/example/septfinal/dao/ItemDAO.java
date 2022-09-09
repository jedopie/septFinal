package com.example.septfinal.dao;

import com.example.septfinal.model.Item;
import com.example.septfinal.model.Items;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAO {
    private static Items items = new Items();

    static {
        items.getItemsList().add(new Item("1", "item 1", "this is item 1", 42.0));
        items.getItemsList().add(new Item("2", "item 2", "this is item 2", 98.0));
        items.getItemsList().add(new Item("3", "item 3", "this is item 3", 10101.0));
    }

    public Items getAllItems() {
        return items;
    }

    public void addItem(Item item) {
        items.getItemsList().add(item);
    }

    public Item getItemById(int id) {
        return items.getItemsList().get(id-1);
    }


}
