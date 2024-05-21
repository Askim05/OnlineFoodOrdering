package Repository;

interface DeliveryAgent {
    void viewPendingOrders(); // User Story 1
    void updateOrderStatus(int orderId, String status); // User Story 2
}
