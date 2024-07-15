package com.FoodDeliveryApplication.Controller;

import com.FoodDeliveryApplication.Entity.Restaurant;
import com.FoodDeliveryApplication.Exception.RestaurantNotFoundException;
import com.FoodDeliveryApplication.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin("*")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    //To save restaurant details
    @PostMapping("/restaurant/save")
    public ResponseEntity<Restaurant> addRestaurants(@RequestBody Restaurant restaurant){
        //implementation to save restaurant details
        try {
            if(restaurant==null){
                throw new Exception("Restaurant object cannot be null");
            } else {
                return ResponseEntity.ok().body(restaurantService.saveRestaurants(restaurant));
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    //To get restaurant details
    @GetMapping("/restaurant/find/{restaurantName}")
    public ResponseEntity<Restaurant> getRestaurants(@PathVariable("restaurantName") String restaurantName)
            throws RestaurantNotFoundException {
        //implementation to get restaurant details
        try {
            if(restaurantName==null){
                throw new Exception("Restaurant id cannot be null");
            } else {
                return ResponseEntity.ok().body(restaurantService.getRestaurantsById(restaurantName));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //To getAll restaurant details
    @GetMapping("/restaurant/findAll")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        //implementation to get all restaurant details
        try {
            return ResponseEntity.ok().body(restaurantService.getAllRestaurants());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    //To update restaurant details
    @PutMapping("/restaurant/update/{id}")
    public ResponseEntity<Restaurant> updateRestaurants(@PathVariable("id") Long id, @RequestBody Restaurant restaurant)
        throws RestaurantNotFoundException {
        //implementation to update restaurant details
        try {
            if(id==null){
                throw new Exception("Restaurant id cannot be null");
            } else if(restaurant==null){
                throw new Exception("Restaurant object cannot be null");
            } else {
                return ResponseEntity.ok().body(restaurantService.updateRestaurant(id, restaurant));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //To delete restaurant details
    @DeleteMapping("/restaurant/delete/{id}")
    public ResponseEntity<Void> deleteRestaurants(@PathVariable("id") Long id)
        throws RestaurantNotFoundException {
        //implementation to delete restaurant details
        try {
            if(id==null){
                throw new Exception("Restaurant id cannot be null");
            } else {
                restaurantService.deleteRestaurant(id);
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
