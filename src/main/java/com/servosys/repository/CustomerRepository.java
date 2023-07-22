package com.servosys.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servosys.model.Customer;
import com.servosys.model.Restaurant;
import com.servosys.model.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);
	 List<Customer> findByUserUserId(Long userId);

	
}

