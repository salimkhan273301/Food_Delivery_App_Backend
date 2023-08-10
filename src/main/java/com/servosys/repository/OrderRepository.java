package com.servosys.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.servosys.model.Order;
import com.servosys.model.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {
 
	 @Query("SELECT o FROM Order o WHERE o.customer.customerId = :customerId")
	List<Order> findByCustomerId(Long customerId);
	 
	 @Query("SELECT o FROM Order o WHERE o.restaurant.restaurantId = :restaurantId")
	List<Order> findByRestaurantId(Long restaurantId);

	 @Modifying
	 @Query("UPDATE Order o SET o.status = :status WHERE o.orderId = :orderId")
	void updateStatus(@Param("status") OrderStatus status,@Param("orderId") Long orderId);
}
