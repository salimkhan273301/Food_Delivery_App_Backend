package com.servosys.service;

import java.util.List;

import com.servosys.model.MenuItem;

public interface MenuItemService {
    MenuItem createMenuItem(MenuItem menuItem);
    MenuItem getMenuItemById(Long itemId);
    // Add more service methods as needed
	List<MenuItem> getMenuItemsByRestaurantId(Long restaurantId);
}
