package com.FoodDeliveryApplication.Service;

import com.FoodDeliveryApplication.Entity.Restaurant;
import com.FoodDeliveryApplication.Exception.RestaurantNotFoundException;
import java.util.List;

public interface RestaurantService {

    Restaurant saveRestaurants(Restaurant restaurant);

    Restaurant getRestaurantsByName(String restaurantName) throws RestaurantNotFoundException;

    List<Restaurant> getAllRestaurants();
    
    Restaurant updateRestaurant(Long id, Restaurant restaurant) throws RestaurantNotFoundException;

    void deleteRestaurant(Long id) throws RestaurantNotFoundException;
}
