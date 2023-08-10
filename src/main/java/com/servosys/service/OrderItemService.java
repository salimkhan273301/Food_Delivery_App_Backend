package com.servosys.service;

import java.util.List;

import com.servosys.model.OrderItem;

public interface OrderItemService {
    OrderItem saveOrderItem(OrderItem orderItem);
    // Add other methods as needed

	List<OrderItem> getOrderItemsbyOrderId(Long orderId);
}
