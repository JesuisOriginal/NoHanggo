package com.example.appteste.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ProductsList {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Product> ITEMS = new ArrayList<Product>();

    public List<Product> getITEMS() {
        return ITEMS;
    }

    public Map<String, Product> getItemMap() {
        return ITEM_MAP;
    }

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Product> ITEM_MAP = new HashMap<String, Product>();



    public static void addItem(Product item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }


    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Product {
        public final String name;
        public final Double value;
        public final Long qtd;
        public final String local;

        public Product(String name, Double value, Long qtd, String local) {
            this.name = name;
            this.value = value;
            this.qtd = qtd;
            this.local = local;
        }
    }
}
