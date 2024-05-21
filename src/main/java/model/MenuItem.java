package model;

public class MenuItem {

    private int id;
    private String name;
    private double price;

    private String description;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private double calorieCount;

    // Constructor
    public MenuItem(int id, String name, double price, double calorieCount,String description) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.calorieCount = calorieCount;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCalorieCount() {
        return calorieCount;
    }

    public void setCalorieCount(double calorieCount) {
        this.calorieCount = calorieCount;
    }

    // Additional Methods


    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", calorieCount=" + calorieCount +
                '}';
    }
}

