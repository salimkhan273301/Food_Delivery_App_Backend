package com.servosys.repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servosys.model.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

	List<MenuItem> findByRestaurant_RestaurantId(Long restaurantId);
    // Add custom queries or methods if needed
	@Query("SELECT m.itemId, m.name, m.price, m.description, m.food_image_url, r.restaurantId	, r.name, r.address FROM MenuItem m INNER JOIN m.restaurant r WHERE r.pincode = :pincode")	  
	List<List<String>> findMenuByPincode(String pincode);
}

