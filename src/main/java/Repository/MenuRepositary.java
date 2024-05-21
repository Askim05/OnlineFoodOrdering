package Repository;

import model.ConcreteRestaurant;
import model.MenuItem;

import java.util.List;

public interface MenuRepositary {

    void browseMenusByCuisine(List<ConcreteRestaurant> restaurants, String cuisineType);
    void addToCart(MenuItem item, int quantity, String specialInstructions);
    void placeOrder(String deliveryAddress, String paymentDetails);
}
