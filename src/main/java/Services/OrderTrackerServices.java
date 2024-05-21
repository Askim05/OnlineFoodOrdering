package Services;

import model.Customer;

import java.util.HashMap;
import java.util.Map;

public class OrderTrackerServices {
    public Map<Integer, String> orderStatusMap; // Map to track order status

    // Constructor
    public OrderTrackerServices() {
        this.orderStatusMap = new HashMap<>();
    }


    // Method to track order status (User Story 3)
    public String trackOrderStatus(int orderId) {
        return orderStatusMap.getOrDefault(orderId, "Order not found");
    }

    // Method to send notifications to customers (User Story 3)
    public void sendNotification(Customer customer, String message) {
        // Implement logic to send notifications via email, SMS, etc.
        System.out.println("Notification sent to " + customer.getEmail() + ": " + message);
    }
}
