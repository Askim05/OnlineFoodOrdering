package controller;

import Repository.RestaurantRepository;
import Repository.RestaurantRepositoryImpl;
import model.ConcreteRestaurant;
import model.MenuItem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RestaurantController {

    public static RestaurantRepository repObject=new RestaurantRepositoryImpl();


    public static List<MenuItem> fetchingDetails(int restid){
        Scanner scan=new Scanner(System.in);
        System.out.println("--ENTER THE MENU ITEM DETAILS---");
        System.out.println("ID of Item: ");
         int itemid=scan.nextInt();
        System.out.println("Name of Item : ");
        String itemName=scan.next();
        System.out.println("Enter Price of the item: ");
        double itemPrice=scan.nextDouble();
        System.out.println("Description : ");
        String itemDes=scan.next();
        MenuItem menuitem = new MenuItem(itemid, itemName, itemPrice, 300, itemDes);

        List<MenuItem> lMenuItem=new ArrayList<>();
        lMenuItem.add(menuitem);


        return lMenuItem;
    }

    public static ConcreteRestaurant creatingRest(int id){
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter the name of Restaurnt: ");
        String restName=scan.next();
        System.out.print("Enter Cuisine Type: ");
        String cuType= scan.next();
        System.out.print("Enter Location: ");
        String loc=scan.next();
        LocalTime openingTime = LocalTime.parse("09:00");
        LocalTime closingTime = LocalTime.parse("21:00");
        List<MenuItem> listmenu =fetchingDetails(id);
        ConcreteRestaurant restaurant = new ConcreteRestaurant(id,restName,cuType,loc, openingTime, closingTime, listmenu, true);
        return restaurant;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);


        try {
            while (true) {
                System.out.println("Welcome to the Restaurant Management System");
                System.out.println("1. Create a new Restaurant");
                System.out.println("2. Retrieve a restaurant by ID");
                System.out.println("3. Update a Restaurant");
                System.out.println("4. Delete a Restaurant");
                System.out.println("5. Exit");
                System.out.println("Select an options: ");

                int choice = sc.nextInt();
                sc.nextLine();
                ConcreteRestaurant concreteRestaurant;

                switch (choice) {
                    case 1:
                        System.out.print("Enter NEW Restaurant ID: ");
                        int id= sc.nextInt();
                        ConcreteRestaurant restaurant=creatingRest(id);
                        repObject.addRestaurant(restaurant);
                        repObject.updateMenuItem(restaurant.getMenuItems().get(0),id);
                        System.out.println("ADDED..!!!");
                        break;
                    case 2:
                        System.out.println("Enter the Restaurant ID which you want to FETCH:: ");
                        id=sc.nextInt();
                        Optional<ConcreteRestaurant> res = repObject.getRestaurantById(id);

                        break;
                    case 3:
                        System.out.println("Enter the Restaurant ID you want to UPDATE:: ");
                        id=sc.nextInt();


                        concreteRestaurant = creatingRest(id);
                        repObject.updateRestaurant(id,concreteRestaurant);
                        System.out.println("UPDATED..!!");

                        break;
                    case 4:
                        System.out.println("Enter the Restaurant ID you want to DELETE:: ");
                        id= sc.nextInt();
                        repObject.deleteRestaurant(id);
                        System.out.println("DELETED SUCCESSFULLY...!!!");

                        break;
                    case 5:
                        System.out.println("Exiting.....");
                        sc.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please Select the correct option.");
                }
            }
        }
        catch (Exception ex) {
                System.out.println("Halted!!!..");
            }

    }
}

