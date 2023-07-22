package com.servosys.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.servosys.exception.UserNotFoundException;
import com.servosys.model.Customer;
import com.servosys.model.Restaurant;
import com.servosys.model.User;
import com.servosys.model.UserRole;
import com.servosys.service.CustomerService;
import com.servosys.service.UserService;
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final UserService userService;

    public CustomerController(CustomerService customerService,UserService userService) {
        this.customerService = customerService;
        this.userService=userService;
    }

    // salim khan jaddupipra
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            // Create a new User object with the provided email, password, and role
            User user = new User();
            user.setEmail(customer.getEmail());
            user.setPassword(customer.getPassword());
            user.setRole(UserRole.CUSTOMER);

            // Save the User object to get the generated user ID
            Long userId = userService.saveUser(user);

            // Set the User object with the generated user ID in the Customer object
            user.setUserId(userId);
            customer.setUser(user);

            // Create the Customer object with the remaining data and save it
            Customer createdCustomer = customerService.createCustomer(customer);

            // Redirect to the login page if registration is successful
            if (createdCustomer != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful! Please login.");
            } else {
                // Registration failed
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed. Please try again.");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        System.out.println(customers.toString());
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Customer>> getRestaurantsByUserId(@PathVariable Long userId) {
        List<Customer> restaurants = customerService.getCustomersByUserId(userId);
        if (!restaurants.isEmpty()) {
            return ResponseEntity.ok(restaurants);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            System.out.println(customer.toString());
            return ResponseEntity.ok(customer);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        try {
            Customer customer = customerService.getCustomerByEmail(email);
            System.out.println(customer.toString());
            return ResponseEntity.ok(customer);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customer);
            return ResponseEntity.ok(updatedCustomer);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
