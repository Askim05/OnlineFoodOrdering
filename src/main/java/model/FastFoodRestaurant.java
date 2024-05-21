package model;

import java.util.HashMap;
import java.util.Map;

public class FastFoodRestaurant extends ConcreteRestaurant {
    private Map<String, Double> menu;

    public FastFoodRestaurant(String name,String cname, String location) {
        super(name,cname, location);
        this.menu = new HashMap<>();
    }

    public Map<String, Double> getMenu() {
        return menu;
    }

    public void addToMenu(String item, double price) {
        menu.put(item, price);
    }

    public void updateMenuItem(String item, double price) {
        if (menu.containsKey(item)) {
            menu.put(item, price);
        } else {
            System.out.println("Item not found in the menu.");
        }
    }
}