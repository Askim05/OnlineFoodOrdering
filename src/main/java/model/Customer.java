package model;

public class Customer {

    private int customerId;
    private String customerName;
    private String address;

    private String Email;

    public Customer(int customerId, String customerName, String address, String Email) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.Email=Email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
