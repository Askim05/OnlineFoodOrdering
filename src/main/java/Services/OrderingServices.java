package Services;

import Repository.MenuRepositary;
import model.ConcreteRestaurant;
import model.MenuItem;

import java.util.*;


public class OrderingServices implements MenuRepositary {
    private List<MenuItem> menus;
    private Map<MenuItem,Integer> cart;
    Scanner sc=new Scanner(System.in);
    static int i=0;

    public OrderingServices() {
        this.menus = new ArrayList<>();
        this.cart=new HashMap<>();
    }

    public void addMenu(MenuItem menu) {
        menus.add(menu);
    }

    //Displaying Available Restaurant
    public static void displayAvailableRestaurants(List<ConcreteRestaurant> restaurants) {
        System.out.println("Available Restaurants:");
        for (ConcreteRestaurant restaurant : restaurants) {
            if (restaurant.isActive()) {
                System.out.println("Restaurant Name: " + restaurant.getName());
                System.out.println("Location: " + restaurant.getLocation());
                System.out.println("Cuisine Type: " + restaurant.getCuisineType());
                System.out.println("Opening Time: " + restaurant.getOpeningTime());
                System.out.println("Closing Time: " + restaurant.getClosingTime());
                System.out.println();
            }
        }
    }

    public void browseMenusByCuisine(List<ConcreteRestaurant> restaurants, String cuisineType) {
        System.out.println("Restaurants with " + cuisineType + " Cuisine:");
        boolean found = false;
        for (ConcreteRestaurant restaurant : restaurants) {
            if (restaurant.getCuisineType() == cuisineType) {
                found = true;
                System.out.println("Restaurant Name: " + restaurant.getName());
                System.out.println("Location: " + restaurant.getLocation());
                System.out.println("Menu Items:");
                List<MenuItem> menuItems = restaurant.getMenuItems();
                if (menuItems.isEmpty()) {
                    System.out.println("No menu items available.");
                } else {
                    for (MenuItem item : menuItems) {
                        System.out.println(item.getName() + " - $" + item.getPrice());
                        System.out.println("Description: " + item.getDescription());
                    }
                }
                System.out.println();
            }
        }
        if (!found) {
            System.out.println("No restaurants with " + cuisineType + " cuisine found.");
        }
    }

    // Method to add items to the cart
    public void addToCart(MenuItem item, int quantity, String specialInstructions) {
        // Check if the item is already in the cart
        if (cart.containsKey(item))
        {
            int existingQuantity = cart.get(item);
            cart.put(item, existingQuantity + quantity);
        } else {
            cart.put(item, quantity);
        }

        System.out.println(quantity + " " + item.getName() + "(s) added to cart.");
    }

    // Method to place an order
    public void placeOrder(String deliveryAddress, String paymentDetails) {

        // Implementation of placing an order goes here
        if(cart.isEmpty()){
            throw new RuntimeException("Cart is Empty..!!");
        }

        double totalAmount=0;

        for(Map.Entry<MenuItem,Integer> entry: cart.entrySet()){
            MenuItem item=entry.getKey();
            int quantity= entry.getValue();
            double cost=item.getPrice()*quantity;
            System.out.println("- " + item.getName() + ": ₹" + item.getPrice() + " x " + quantity + " = ₹" + cost);
            totalAmount+=cost;
        }
        System.out.println("Total Amount: ₹" + totalAmount);
        System.out.println("Delivery Address: " + deliveryAddress);
        System.out.println("Payment Details: " + paymentDetails);

        // Clear the cart after placing the order
        cart.clear();
        System.out.println("Your order has been placed successfully!");
    }
}
