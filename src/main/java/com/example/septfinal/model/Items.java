package com.example.septfinal.model;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private ArrayList<Item> itemList;

    public List<Item> getItemsList() {
        if(itemList == null) {
            itemList = new ArrayList<>();
        }
        return itemList;
    }

    public void setItemsList(ArrayList<Item> ItemsList) {
        this.itemList = ItemsList;
    }

    public void deleteByIndex(int index) {
        this.itemList.remove(index);
    }
}
