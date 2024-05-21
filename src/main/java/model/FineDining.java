package model;

class FineDiningRestaurant extends ConcreteRestaurant {
    private String chefName;

    public FineDiningRestaurant(String name,String ctype, String location, String chefName) {
        super(name,ctype,location);
        this.chefName = chefName;
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }
}