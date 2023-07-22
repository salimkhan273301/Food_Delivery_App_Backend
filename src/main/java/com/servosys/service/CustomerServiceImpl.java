package com.servosys.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.servosys.exception.UserNotFoundException;
import com.servosys.model.Customer;
import com.servosys.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        // Implement the logic to create a customer
        // Example:
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) throws UserNotFoundException {
        // Implement the logic to retrieve a customer by ID
        // Example:
        return customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));
    }
    
    public Customer getCustomerByEmail(String email) throws UserNotFoundException {
        // Implement the logic to retrieve a customer by email
        // Example:
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new UserNotFoundException("Customer not found");
        }
    }


    @Override
    public Customer updateCustomer(Long id, Customer customer) throws UserNotFoundException {
        // Implement the logic to update a customer
        // Example:
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));

        // Update the customer properties
        existingCustomer.setName(customer.getName());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) throws UserNotFoundException {
        // Implement the logic to delete a customer
        // Example:
        if (!customerRepository.existsById(id)) {
            throw new UserNotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

	@Override
	public List<Customer> getCustomersByUserId(Long userId) {
		
		return customerRepository.findByUserUserId(userId);
	}
}
