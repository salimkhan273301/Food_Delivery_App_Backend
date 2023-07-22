package com.servosys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.servosys.model.MenuItem;
import com.servosys.service.MenuItemService;
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/menu-items")
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
    
    @GetMapping("/myrestaurent/{restaurantId}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByRestaurantId(@PathVariable Long restaurantId) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByRestaurantId(restaurantId);
        if (!menuItems.isEmpty()) {
            return ResponseEntity.ok(menuItems);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add more endpoints for updating, deleting, and listing menu items
}

