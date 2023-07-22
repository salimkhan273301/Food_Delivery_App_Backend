package com.servosys.service;

import com.servosys.model.Restaurant;




import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantById(Long id);

    Restaurant updateRestaurant(Long id, Restaurant restaurant);

    boolean deleteRestaurant(Long id);

	

	List<Restaurant> getRestaurantsByUserId(Long userId);

	List<Restaurant> getRestaurantsByPincode(String pincode);
}
