package com.servosys.controller;

import com.servosys.exception.UserNotFoundException;
import com.servosys.model.Customer;
import com.servosys.model.Restaurant;
import com.servosys.model.User;
import com.servosys.model.UserRole;
import com.servosys.service.RestaurantService;
import com.servosys.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final UserService userService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService=userService;
    }
    
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        // Create a new User object with the provided email, password, and role
		User user = new User();
		user.setEmail(restaurant.getEmail());
		user.setPassword(restaurant.getPassword());
		user.setRole(UserRole.RESTAURANT_OWNER);

		// Save the User object to get the generated user ID
		Long userId = userService.saveUser(user);

		// Set the User object with the generated user ID in the Restaurant object
		user.setUserId(userId);
		restaurant.setUser(user);

		// Create the Restaurant object with the remaining data and save it
		Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

		// Redirect to the login page if registration is successful
		if (createdRestaurant != null) {
		    return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
		} else {
		    // Registration failed
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }



   

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/area/{pincode}")
    public ResponseEntity<List<Restaurant>> getRestaurantsByPincode(@PathVariable String pincode) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsByPincode(pincode);
        return ResponseEntity.ok(restaurants);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Restaurant>> getRestaurantsByUserId(@PathVariable Long userId) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsByUserId(userId);
        if (!restaurants.isEmpty()) {
            return ResponseEntity.ok(restaurants);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, restaurant);
        if (updatedRestaurant != null) {
            return ResponseEntity.ok(updatedRestaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        boolean deleted = restaurantService.deleteRestaurant(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
