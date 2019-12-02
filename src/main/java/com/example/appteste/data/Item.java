package com.example.appteste.data;

import java.util.HashMap;
import java.util.Map;

public class Item {
    private String itemName;
    private double itemPrice;
    private int quantity;
    private int barcode;

    public Item(String itemName, double itemPrice, int quantity, int barcode) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.barcode = barcode;
    }


    public Map<Integer, Item> item(){
        Map<Integer, Item> product = new HashMap<>();
        product.put(this.barcode, this);
        return product;
    }
}
