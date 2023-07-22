package com.servosys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servosys.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	 List<Restaurant> findByUserUserId(Long userId);

	List<Restaurant> findByPincode(String pincode);
}
