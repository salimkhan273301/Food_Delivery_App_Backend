package com.servosys.service;
import java.util.List;

import com.servosys.exception.UserNotFoundException;
import com.servosys.model.Customer;
import com.servosys.model.Restaurant;
public interface CustomerService {
    Customer createCustomer(Customer customer) throws UserNotFoundException;
    Customer getCustomerById(Long id) throws UserNotFoundException;
    Customer updateCustomer(Long id, Customer customer) throws UserNotFoundException;
    void deleteCustomer(Long id) throws UserNotFoundException;
	Customer getCustomerByEmail(String email) throws UserNotFoundException;
	List<Customer> getAllCustomers();
	List<Customer> getCustomersByUserId(Long userId);
}
