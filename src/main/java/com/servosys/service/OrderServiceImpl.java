package com.servosys.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servosys.model.Order;
import com.servosys.model.OrderStatus;
import com.servosys.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    
    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

	@Override
	public List<Order> getAllOrdersOfRestaurant(Long restaurantId) {
		// TODO Auto-generated method stub
		
		System.out.println("***********************************");
		return orderRepository.findByRestaurantId(restaurantId);
	}

	@Transactional 
	public void updateStatusInOrder(OrderStatus status, Long orderId) {
		 orderRepository.updateStatus(status, orderId);
		
	}
	 @Override
	    public List<Order> getAllOrders() {
	        return orderRepository.findAll();
	    }
}

