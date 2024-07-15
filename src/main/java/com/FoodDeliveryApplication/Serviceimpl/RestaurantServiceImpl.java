package com.FoodDeliveryApplication.Serviceimpl;

import com.FoodDeliveryApplication.Entity.Restaurant;
import com.FoodDeliveryApplication.Exception.RestaurantNotFoundException;
import com.FoodDeliveryApplication.Repository.RestaurantRepository;
import com.FoodDeliveryApplication.Service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    private static final Pattern SAFE_TEXT_PATTERN = Pattern.compile("^[a-zA-Z0-9_.@\\- '()]*$");
    private static final Pattern ADDRESS_PATTERN = Pattern.compile("^[a-zA-Z0-9 ,.'-]+$");
    private static final Pattern CUISINE_PATTERN = Pattern.compile("^[a-zA-Z ]+$");
    private static final Pattern RATING_PATTERN = Pattern.compile("^[0-5](\\.[0-9])?$");

    @Override
    public Restaurant saveRestaurants(Restaurant restaurant) {
        logger.info("Saving restaurants: {}", restaurant);
        try {
            validateRestaurantData(restaurant);
            return restaurantRepository.save(restaurant);
        } catch(ValidationException e){
            throw e;
        } catch(Exception e){
            throw new RuntimeException("Failed to save restaurants", e);
        }
    }

    @Override
    public Restaurant getRestaurantsById(String restaurantName) {
        logger.info("Getting restaurants by id: {}", restaurantName);
        try {
            return restaurantRepository.findByRestaurantName(restaurantName).orElseThrow(() ->
                    new RestaurantNotFoundException("Restaurants not found"));
        } catch(Exception e){
            throw new RuntimeException("Failed to get restaurants", e);
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        logger.info("Getting all restaurants");
        try {
            return restaurantRepository.findAll();
        } catch(Exception e){
            throw new RuntimeException("Failed to get all restaurants", e);
        }
    }

    @Override
    public Restaurant updateRestaurant(Long id, Restaurant restaurant) throws RestaurantNotFoundException {
        logger.info("Updating restaurant by id: {}, data: {}", id, restaurant);
        Restaurant existingRestaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new RestaurantNotFoundException("Restaurant not found"));
        existingRestaurant.setRestaurantName(restaurant.getRestaurantName());
        existingRestaurant.setRestaurantAddress(restaurant.getRestaurantAddress());
        existingRestaurant.setCuisine(restaurant.getCuisine());
        existingRestaurant.setRating(restaurant.getRating());
        validateRestaurantData(existingRestaurant);
        return restaurantRepository.save(existingRestaurant);
    }

    @Override
    public void deleteRestaurant(Long id) throws RestaurantNotFoundException {
        logger.info("Deleting restaurant by id: {}", id);

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Restaurant existingRestaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new RestaurantNotFoundException("Restaurant not found"));

        restaurantRepository.deleteById(id);
        logger.info("Successfully deleted restaurant with id: {}", id);
    }


    // Validation checks using if-else block conditions
    private void validateRestaurantData(Restaurant restaurant) {
        if (restaurant.getRestaurantName() == null || !SAFE_TEXT_PATTERN.matcher(restaurant.getRestaurantName()).matches()) {
            throw new ValidationException("Invalid name format. Only alphanumeric characters, underscores, dots, @, -, spaces, and parentheses are allowed.");
        }

        if (restaurant.getRestaurantAddress() == null || !ADDRESS_PATTERN.matcher(restaurant.getRestaurantAddress()).matches()) {
            throw new ValidationException("Invalid address format. Only alphanumeric characters, spaces, commas, periods, apostrophes, and hyphens are allowed.");
        }

        if (restaurant.getCuisine() == null || !CUISINE_PATTERN.matcher(restaurant.getCuisine()).matches()) {
            throw new ValidationException("Invalid cuisine format. Only alphabetic characters and spaces are allowed.");
        }

        if (restaurant.getRating() == null || !RATING_PATTERN.matcher(restaurant.getRating().toString()).matches()) {
            throw new ValidationException("Invalid rating format. Only numeric characters and a decimal point are allowed.");
        }
    }

}
