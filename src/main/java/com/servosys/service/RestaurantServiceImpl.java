package com.servosys.service;

import com.servosys.model.Restaurant;
import com.servosys.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        return optionalRestaurant.orElse(null);
    }

    @Override
    public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            restaurant.setRestaurantId(id);
            return restaurantRepository.save(restaurant);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteRestaurant(Long id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            restaurantRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

	@Override
	public List<Restaurant> getRestaurantsByUserId(Long userId) {
		
		 return restaurantRepository.findByUserUserId(userId);
	}
	
	
	 @Override
	    public List<Restaurant> getRestaurantsByPincode(String pincode) {
	        return restaurantRepository.findByPincode(pincode);
	    }

}
