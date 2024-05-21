package Repository;

import Exception.RecordNotFoundException;
import Exception.RestaurantNotFoundException;
import model.ConcreteRestaurant;
import model.MenuItem;
import util.JdbcConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class RestaurantRepositoryImpl implements RestaurantRepository {

    public List<ConcreteRestaurant> restaurants;

    public RestaurantRepositoryImpl() {
        this.restaurants = new ArrayList<>();
    }


    @Override
    public void addRestaurant(ConcreteRestaurant restaurant) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcConnection.getConnection();

            String query = "INSERT INTO restaurant  VALUES (?,?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);

            statement.setInt(1,restaurant.getRestaurantId());
            statement.setString(2, restaurant.getName());
            statement.setString(4, restaurant.getCuisineType());
            statement.setString(3, restaurant.getLocation());
            LocalTime localDate = LocalTime.from(restaurant.getOpeningTime());//For reference
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedString = localDate.format(formatter);
            statement.setString(6, formattedString);
            statement.setBoolean(5, restaurant.isActive());
            statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println("RESTAURANT WITH ID "+restaurant.getRestaurantId()+" ALREDY EXISTS..!!!");
            throw new RuntimeException();

        }
    }

    @Override
    public void updateRestaurant(int id, ConcreteRestaurant concreteRestaurant) throws SQLException {
        Connection connection = JdbcConnection.getConnection();
        String sql = "UPDATE restaurant SET name=?, cusine_type=?, location=?, is_active=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        try  {
            statement.setString(1, concreteRestaurant.getName());
            statement.setString(2, concreteRestaurant.getCuisineType());
            statement.setString(3, concreteRestaurant.getLocation());
            statement.setBoolean(4, concreteRestaurant.isActive());
            statement.setLong(5, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Restaurant with ID " + id + " updated successfully.");
            } else {
                System.out.println("Failed to update restaurant with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error updating restaurant with ID " + id + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteRestaurant(int id) throws SQLException {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "delete from restaurant where id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowCount = statement.executeUpdate();
            if (rowCount == 0) {
                throw new RestaurantNotFoundException();
            }
            JdbcConnection.closeConnection();
        } catch (RestaurantNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void updateMenuItem(MenuItem item,int restId) throws SQLException{
        try{
            Connection connection = JdbcConnection.getConnection();
            String query = "INSERT INTO menu_item  VALUES (?,?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, item.getId());
            statement.setString(2, item.getName());
            statement.setDouble(3, item.getPrice());
            statement.setInt(4, restId);
            statement.executeUpdate();
            JdbcConnection.closeConnection();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Optional<ConcreteRestaurant> getRestaurantById(int id) {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "SELECT * FROM restaurant WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet queryResult = statement.executeQuery();

            Optional<ConcreteRestaurant> restaurant = Optional.empty();
            if (queryResult.next()) {
                mapResultSetToRestaurant(queryResult);
            }

            queryResult.close();
            statement.close();
            connection.close();

            return null;
        } catch (SQLException ex) {
            throw new RecordNotFoundException(ex.getMessage());
        }
    }

    private void mapResultSetToRestaurant(ResultSet resultSet) throws SQLException {

        Connection connection = JdbcConnection.getConnection();
        int restaurantId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String cuisineType = resultSet.getString("cusine_type");
        String location = resultSet.getString("location");
        boolean isActive = resultSet.getBoolean("is_active");
        System.out.println("Restaurant Details...");
        System.out.println("Name:"+name+" cuisine Type: "+cuisineType+" Location: "+location+" Available: "+isActive);
        List<MenuItem> menuItems = fetchMenuItemsForRestaurant(restaurantId, connection);
    }

    public List<MenuItem> fetchMenuItemsForRestaurant(int restaurantId, Connection connection) throws SQLException {
        String query = "SELECT * FROM menu_item WHERE restaurant_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, restaurantId);
        ResultSet resultSet = statement.executeQuery();

        List<MenuItem> menuItems = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            //String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            //double calorieCount= resultSet.getDouble("calorieCount");
            MenuItem menuItem = new MenuItem(id, name,price,300,"Veg");
            System.out.println("\n MENU ITEMS:: \n Name: "+name+"\nPrice: "+price);
            menuItems.add(menuItem);
        }


        resultSet.close();
        statement.close();

        return menuItems;
    }

    @Override
    public List<ConcreteRestaurant> findRestaurantByLocation(String location) throws SQLException{

        Connection connection = JdbcConnection.getConnection();
        String query = "SELECT * FROM restaurant WHERE location=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, location);
        ResultSet resultSet = statement.executeQuery();
        System.out.println(resultSet);
        while(resultSet.next()){
            int restaurantId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String cuisineType = resultSet.getString("cusine_type");

            boolean isActive = resultSet.getBoolean("is_active");
            System.out.println("Restaurant Details...");
            System.out.println("Name:"+name+" \ncuisine Type: "+cuisineType+" \nLocation: "+location+" \nAvailable: "+isActive);
        }
        return restaurants.stream()
                .filter(restaurant -> restaurant.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }
}
