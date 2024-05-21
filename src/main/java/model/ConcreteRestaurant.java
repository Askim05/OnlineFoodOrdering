package model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

public class ConcreteRestaurant {

    @Setter
    @Getter
    private int restaurantId;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String cuisineType;
    @Setter
    @Getter
    private String location;
    @Getter
    @Setter
    private LocalTime openingTime;
    @Getter
    @Setter
    private LocalTime closingTime;
    @Getter
    @Setter
    private List<MenuItem> menuItems;
    @Getter
    @Setter
    private boolean isActive;

    public ConcreteRestaurant(int restaurantId, String name, String cuisineType, String location, LocalTime openingTime, LocalTime closingTime, List<MenuItem> menuItems, boolean isActive) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisineType = cuisineType;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.menuItems = menuItems;
        this.isActive = isActive;
    }



    // Additional Methods

    // Method to check if the restaurant is open at a given time
    public boolean isOpen(LocalTime time) {
        return !time.isBefore(openingTime) && !time.isAfter(closingTime);
    }

    // Method to add a menu item
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    // Method to remove a menu item
    public void removeMenuItem(MenuItem item) {
        menuItems.remove(item);
    }


    // Method to display the menu of the restaurant
    public void displayMenu() {
        System.out.println("Menu Items:");
        for (MenuItem item : menuItems) {
            System.out.println(item.getName() + " - $" + item.getPrice());
            System.out.println("Description: " + item.getDescription());
        }
    }

    // Method to display basic information about the restaurant
    public void displayBasicInfo() {
        System.out.println("Restaurant Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Cuisine Type: " + cuisineType);
        System.out.println("Opening Time: " + openingTime);
        System.out.println("Closing Time: " + closingTime);
        System.out.println("Active Status: " + isActive);
    }

    // Method to display detailed information about the restaurant
    public void displayDetailedInfo() {
        displayBasicInfo();
        System.out.println();
        displayMenu();
    }

    // Method to display restaurant information
    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", cuisineType=" + cuisineType +
                ", location='" + location + '\'' +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                ", menuItems=" + menuItems +
                ", isActive=" + isActive +
                '}';
    }
}
