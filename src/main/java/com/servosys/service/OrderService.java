package com.servosys.service;

import java.util.List;

import com.servosys.model.Order;
import com.servosys.model.OrderStatus;

public interface OrderService {

	List<Order> getAllOrders(Long customerId);

	void deleteOrder(Long orderId);

	Order saveOrder(Order order);

	Order getOrderById(Long orderId);

	List<Order> getAllOrdersOfRestaurant(Long restaurantId);

	void updateStatusInOrder(OrderStatus status, Long orderId);

	List<Order> getAllOrders();

}
