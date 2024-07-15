package com.FoodDeliveryApplication.Controller;

import com.FoodDeliveryApplication.Entity.Restaurant;
import com.FoodDeliveryApplication.Exception.RestaurantNotFoundException;
import com.FoodDeliveryApplication.Service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin("*")
public class RestaurantController {

    private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    // To save restaurant details
    @PostMapping("/restaurant/save")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        logger.info("Request to save restaurant: {}", restaurant);
        try {
            return ResponseEntity.ok(restaurantService.saveRestaurants(restaurant));
        } catch (IllegalArgumentException e) {
            logger.error("Invalid restaurant data : {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            logger.error("Failed to save restaurant: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    // To get restaurant details
    @GetMapping("/restaurant/find/{restaurantName}")
    public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable("restaurantName") String restaurantName) {
        logger.info("Request to get restaurant by name: {}", restaurantName);
        try {
            return ResponseEntity.ok(restaurantService.getRestaurantsByName(restaurantName));
        } catch (RestaurantNotFoundException e) {
            logger.error("Restaurant not found : {}", e.getMessage());
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            logger.error("Failed to get restaurant: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    // To get all restaurant details
    @GetMapping("/restaurant/findAll")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        logger.info("Request to get all restaurants");
        try {
            return ResponseEntity.ok(restaurantService.getAllRestaurants());
        } catch (Exception e) {
            logger.error("Failed to get all restaurants: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    // To update restaurant details
    @PutMapping("/restaurant/update/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") Long id,@RequestBody Restaurant restaurant) {
        logger.info("Request to update restaurant with id: {}, data: {}", id, restaurant);
        try {
            return ResponseEntity.ok(restaurantService.updateRestaurant(id, restaurant));
        } catch (RestaurantNotFoundException e) {
            logger.error("Restaurants not found: {}", e.getMessage());
            return ResponseEntity.status(404).body(null);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid restaurant data: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            logger.error("Failed to update restaurant: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    // To delete restaurant details
    @DeleteMapping("/restaurant/delete/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Long id) {
        logger.info("Request to delete restaurant with id: {}", id);
        try {
            restaurantService.deleteRestaurant(id);
            return ResponseEntity.noContent().build();
        } catch (RestaurantNotFoundException e) {
            logger.error("Restaurant not found: {}", e.getMessage());
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            logger.error("Failed to delete restaurant: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
