package com.servosys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servosys.model.MenuItem;
import com.servosys.repository.MenuItemRepository;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem getMenuItemById(Long itemId) {
        return menuItemRepository.findById(itemId).orElse(null);
    }

    public List<MenuItem> getMenuItemsByRestaurantId(Long restaurantId) {
        return menuItemRepository.findByRestaurant_RestaurantId(restaurantId);
    }
    // Implement more service methods as needed

	@Override
	public List<List<String>> getMenuItemsByPincode(Long pincode) {
		 return menuItemRepository.findMenuByPincode( String.valueOf(pincode) );
	}

	@Override
	public void deleteMenuItem(Long itemId) {
		menuItemRepository.deleteById(itemId);
		
	}

	@Override
	public MenuItem updateMenuItem(MenuItem updatedmenuItem) {
		
		return menuItemRepository.save(updatedmenuItem) ;
	}

	 @Override
	    public List<MenuItem> getAllMenuItems() {
	        return menuItemRepository.findAll();
	    }
}
