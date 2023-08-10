package com.servosys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.servosys.model.MenuItem;
import com.servosys.service.MenuItemService;
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem createdMenuItem = menuItemService.createMenuItem(menuItem);
        return ResponseEntity.ok(createdMenuItem);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long itemId) {
        MenuItem menuItem = menuItemService.getMenuItemById(itemId);
        if (menuItem != null) {
            return ResponseEntity.ok(menuItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // this is a get
    @GetMapping("/myrestaurent/{restaurantId}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByRestaurantId(@PathVariable Long restaurantId) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByRestaurantId(restaurantId);
        if (!menuItems.isEmpty()) {
            return ResponseEntity.ok(menuItems);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // this is a get
    @GetMapping("/bypincode/{pincode}")
    public ResponseEntity<List<Map<String, Object>>> getMenuItemsByPincode(@PathVariable Long pincode) {
        List<List<String>> menuItems = menuItemService.getMenuItemsByPincode(pincode);
        List<Map<String, Object>> transformedMenuItems = new ArrayList<>();

        System.out.print(menuItems);
        
        for (List<String> menuItemData : menuItems) {
        	
            Map<String, Object> transformedMenuItem = new HashMap<>();
            transformedMenuItem.put("id", menuItemData.get(0));
            transformedMenuItem.put("name", menuItemData.get(1));
            transformedMenuItem.put("price", menuItemData.get(2));
            transformedMenuItem.put("description", menuItemData.get(3));
            transformedMenuItem.put("foodImageUrl", menuItemData.get(4));
            transformedMenuItem.put("restaurantId", menuItemData.get(5));
            transformedMenuItem.put("restaurantName", menuItemData.get(6));
            transformedMenuItem.put("address", menuItemData.get(7));
            
            transformedMenuItems.add(transformedMenuItem);
        }

        if (!transformedMenuItems.isEmpty()) {
            return ResponseEntity.ok(transformedMenuItems);
        } else {
           return ResponseEntity.notFound().build();
        }
    }
    
    
    @PutMapping("/updatemenu/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long itemId, @RequestBody MenuItem updatedMenuItem) {
        MenuItem menuItem = menuItemService.getMenuItemById(itemId);
        if (menuItem != null) {
            menuItem.setName(updatedMenuItem.getName());
            menuItem.setDescription(updatedMenuItem.getDescription());
            menuItem.setPrice(updatedMenuItem.getPrice());
            menuItem.setFood_image_url(updatedMenuItem.getFood_image_url());
           
            // Save the updated menuItem in the service
            MenuItem updatedItem = menuItemService.updateMenuItem(menuItem);
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long itemId) {
        MenuItem menuItem = menuItemService.getMenuItemById(itemId);
        if (menuItem != null) {
            // Delete the menuItem from the service
            menuItemService.deleteMenuItem(itemId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        if (!menuItems.isEmpty()) {
            return ResponseEntity.ok(menuItems);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


  
    
   
}

