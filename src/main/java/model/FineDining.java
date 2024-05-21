package model;

import java.time.LocalTime;
import java.util.List;

class FineDiningRestaurant extends ConcreteRestaurant {
    private String chefName;

    public FineDiningRestaurant(int restaurantId, String name, String cuisineType, String location, LocalTime openingTime, LocalTime closingTime, List<MenuItem> menuItems, boolean isActive) {
        super(restaurantId, name, cuisineType, location, openingTime, closingTime, menuItems, isActive);
    }


    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }
}