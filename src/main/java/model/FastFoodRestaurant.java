package model;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastFoodRestaurant extends ConcreteRestaurant {
    private Map<String, Double> menu;

    public FastFoodRestaurant(int restaurantId, String name, String cuisineType, String location, LocalTime openingTime, LocalTime closingTime, List<MenuItem> menuItems, boolean isActive) {
        super(restaurantId, name, cuisineType, location, openingTime, closingTime, menuItems, isActive);
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