package controller;

import Repository.DeliveryAgentImpl;
import Services.OrderTrackerServices;
import model.Customer;
import model.Order;

public class OrderTrackerServiceController {
    public static void main(String[] args) {

        // Create some sample orders
        Order order1 = new Order(1, "123 Main St, City A", "123-456-7890");
        Order order2 = new Order(2, "456 Elm St, City B", "987-654-3210");
        Order order3 = new Order(3, "789 Oak St, City C", "456-789-0123");

        // Create some sample customers
        Customer customer1 = new Customer(1,"Alice","Bengaluru","alice@example.com");
        Customer customer2 = new Customer(2,"Bob","Nagpur" ,"bob@example.com");

        System.out.println("==================== Order Successfully Assign to the delivery agent ==================");
        // Create a delivery agent
        DeliveryAgentImpl deliveryAgent = new DeliveryAgentImpl();

        // Assign orders to the delivery agent
        deliveryAgent.AssignedOrders(order1);
        deliveryAgent.AssignedOrders(order2);
        deliveryAgent.AssignedOrders(order3);

        // View pending orders
        System.out.println("1.  View Pending Orders:");
        deliveryAgent.viewPendingOrders();

        // View updateOrderStatus
        System.out.println("=============== updating the Order Status: ===============");
        deliveryAgent.updateOrderStatus(1,"Completed");


        // Create an OrderTracker instance
        OrderTrackerServices orderTrackerServices = new OrderTrackerServices();


        // Update order statuses
        orderTrackerServices.orderStatusMap.put(1, "Picked Up");
        orderTrackerServices.orderStatusMap.put(2, "En Route");

        System.out.println("=================== Tracking the order for the Customers =================");
        // Track order status for customer1
        String status1 = orderTrackerServices.trackOrderStatus(order1.getOrderId());
        System.out.println("Order Status for Customer " + customer1.getCustomerName() + ": " + status1);

        // Track order status for customer2
        String status2 = orderTrackerServices.trackOrderStatus(order2.getOrderId());
        System.out.println("Order Status for Customer " + customer2.getCustomerName() + ": " + status2);

        System.out.println("=================== Sending notifications to customers =================");
        // Send notifications to customers
        orderTrackerServices.sendNotification(customer1, "Your order has been picked up!");
        orderTrackerServices.sendNotification(customer2, "Your order is on its way!");
    }

}

