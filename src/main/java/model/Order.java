package model;
public class Order {
    private int orderId;
    private String deliveryAddress;
    private String contactNumber;
    private String status;

    // Constructor
    public Order(int orderId, String deliveryAddress, String contactNumber) {
        this.orderId = orderId;
        this.deliveryAddress = deliveryAddress;
        this.contactNumber = contactNumber;
        this.status = "Pending"; // Initial status
    }
    // Getters and setters for order details and status

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
