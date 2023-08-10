package com.servosys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.servosys.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	 @Query("SELECT oi FROM OrderItem oi WHERE oi.order.orderId = :orderId")
	   
	List<OrderItem> findByOrderId(Long orderId);
    // You can add custom query methods here if needed
}