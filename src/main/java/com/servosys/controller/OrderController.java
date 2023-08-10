package com.servosys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.servosys.exception.UserNotFoundException;
import com.servosys.model.Customer;
import com.servosys.model.MenuItem;
import com.servosys.model.Order;
import com.servosys.model.OrderItem;
import com.servosys.model.OrderStatus;
import com.servosys.model.Restaurant;
import com.servosys.service.CustomerService;
import com.servosys.service.MenuItemService;
import com.servosys.service.OrderItemService;
import com.servosys.service.OrderService;
import com.servosys.service.RestaurantService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;
    private final OrderItemService orderItemService;
    
    @Autowired
    public OrderController(OrderService orderService,CustomerService customerService,RestaurantService restaurantService, MenuItemService menuItemService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.customerService=customerService;
        this.restaurantService=restaurantService;
		this.menuItemService = menuItemService;
		this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<Map<String, Object>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Order o : orders) {
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("orderId", o.getOrderId());
            orderMap.put("restaurantName", o.getRestaurant().getName());
            orderMap.put("customerName", o.getCustomer().getName());
            orderMap.put("orderDate", o.getOrderDate());
            orderMap.put("totalAmount", o.getTotalAmount());
            orderMap.put("status", o.getStatus());
           

            List<Map<String, Object>> orderItemsList = new ArrayList<>();
            List<OrderItem> orderItems = orderItemService.getOrderItemsbyOrderId(o.getOrderId());
            for (OrderItem orderItem : orderItems) {
                Map<String, Object> orderItemMap = new HashMap<>();
//                orderItemMap.put("orderItemId", orderItem.getOrderItemId());
                orderItemMap.put("menuItem", orderItem.getMenuItem().getName());
                orderItemMap.put("quantity", orderItem.getQuantity());
//                orderItemMap.put("price", orderItem.getPrice());

                orderItemsList.add(orderItemMap);
            }

            orderMap.put("orderItems", orderItemsList);
           
           
            
            response.add(orderMap);
        }

        return response;
        }
        
        
        
    

    
    @GetMapping("/customer/{customerId}")
    public List<Map<String, Object>> getAllOrdersOfCustomer(@PathVariable Long customerId) {
        List<Order> orders = orderService.getAllOrders(customerId);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Order o : orders) {
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("orderId", o.getOrderId());
            orderMap.put("restaurantName", o.getRestaurant().getName());
            orderMap.put("orderDate", o.getOrderDate());
            orderMap.put("totalAmount", o.getTotalAmount());
            orderMap.put("status", o.getStatus());

            List<Map<String, Object>> orderItemsList = new ArrayList<>();
            List<OrderItem> orderItems = orderItemService.getOrderItemsbyOrderId(o.getOrderId());
            for (OrderItem orderItem : orderItems) {
                Map<String, Object> orderItemMap = new HashMap<>();
//                orderItemMap.put("orderItemId", orderItem.getOrderItemId());
                orderItemMap.put("menuItem", orderItem.getMenuItem().getName());
//                orderItemMap.put("quantity", orderItem.getQuantity());
//                orderItemMap.put("price", orderItem.getPrice());

                orderItemsList.add(orderItemMap);
            }

            orderMap.put("orderItems", orderItemsList);
            response.add(orderMap);
        }

        return response;
    }



    @GetMapping("/restaurant/{restaurantId}")
    public List<Map<String, Object>> getAllOrdersOfRestaurant(@PathVariable Long restaurantId) {
        List<Order> orders = orderService.getAllOrdersOfRestaurant(restaurantId);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Order o : orders) {
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("orderId", o.getOrderId());
            orderMap.put("customerName", o.getCustomer().getName());
            orderMap.put("orderDate", o.getOrderDate());
            orderMap.put("totalAmount", o.getTotalAmount());
            orderMap.put("status", o.getStatus());

            List<Map<String, Object>> orderItemsList = new ArrayList<>();
            List<OrderItem> orderItems = orderItemService.getOrderItemsbyOrderId(o.getOrderId());
            for (OrderItem orderItem : orderItems) {
                Map<String, Object> orderItemMap = new HashMap<>();
//                orderItemMap.put("orderItemId", orderItem.getOrderItemId());
                orderItemMap.put("menuItem", orderItem.getMenuItem().getName());
//                orderItemMap.put("quantity", orderItem.getQuantity());
//                orderItemMap.put("price", orderItem.getPrice());

                orderItemsList.add(orderItemMap);
            }

            orderMap.put("orderItems", orderItemsList);
            response.add(orderMap);
        }

        System.out.println("My orders data::::"+response);
        return response;
    }


    
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/updateStatus/{orderId}")
    public ResponseEntity<String> updateStatusInOrder(@RequestBody OrderStatus status, @PathVariable Long orderId) {
        try {
            // Call the service method to update the status in the order
            orderService.updateStatusInOrder(status, orderId);
            
            // Return a success response with HTTP status 200
            return ResponseEntity.ok("Status updated successfully");
        } catch (Exception e) {
            // Return an error response with HTTP status 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Status update failed: " + e.getMessage());
        }
    }
   




    
    @PostMapping("/bookOrder")
    public Order saveOrder(@RequestBody Map<String,Object> payload) throws UserNotFoundException {
    	
    	Order order = new Order();
    	Customer customer = customerService.getCustomerById( ( (Integer) payload.get("customer_id")).longValue());
    	Restaurant restaurant=restaurantService.getRestaurantById( ( (Integer)payload.get("restaurant_id")).longValue());
    	order.setCustomer(customer);
    	order.setRestaurant(restaurant);
    	order.setStatus(OrderStatus.BOOKED);
    	order.setOrderDate(new Date());
    	order.setTotalAmount(Double.parseDouble(payload.get("totalAmount").toString()));
    	 System.out.println(order.toString());
//   	Order order=new Order();
//    	order.setCustomer()
  
    	 
    Order savedOrder=	 orderService.saveOrder(order);
    	 @SuppressWarnings("unchecked")
		List<Map<String, Object>> itemsList = (List<Map<String, Object>>) payload.get("items");
    	 
    	 for (Map<String, Object> item : itemsList) {
             OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setPrice(Double.parseDouble(item.get("price").toString()));
            orderItem.setQuantity((Integer) item.get("quantity") );
            MenuItem menuItem = menuItemService.getMenuItemById(( (Integer) item.get("item_id")).longValue());
            orderItem.setMenuItem(menuItem);
            
            orderItemService.saveOrderItem(orderItem);
            
         }
    	 
    	 
    	
    

        return savedOrder;
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }
}
