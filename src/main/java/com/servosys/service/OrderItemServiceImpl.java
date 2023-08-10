package com.servosys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servosys.model.OrderItem;
import com.servosys.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

	@Override
	public List<OrderItem> getOrderItemsbyOrderId(Long orderId) {
		// TODO Auto-generated method stub
		return orderItemRepository.findByOrderId(orderId);
	}
    


    // Add other methods implementations as needed
}
