package Repository;

import model.ConcreteRestaurant;
import model.MenuItem;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {

    void addRestaurant(ConcreteRestaurant restaurant) throws SQLException;

    void updateRestaurant(int restaurantId,ConcreteRestaurant conres) throws SQLException;

    void deleteRestaurant(int restaurantId) throws SQLException;


    void updateMenuItem(MenuItem item,int restId)throws SQLException;



    Optional<ConcreteRestaurant>  getRestaurantById(int id);

    List<ConcreteRestaurant> findRestaurantByLocation(String location) throws SQLException;

}
