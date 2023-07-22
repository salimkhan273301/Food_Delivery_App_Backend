package com.servosys.service;

import org.springframework.stereotype.Service;


import com.servosys.exception.InvalidCredentialsException;
import com.servosys.exception.LoginException;
import com.servosys.exception.UserNotFoundException;

import com.servosys.model.User;
import com.servosys.model.UserRole;
import com.servosys.repository.CustomerRepository;
import com.servosys.repository.UserRepository;
import com.servosys.responses.LoginResponse;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        
    }

    @Override
    public LoginResponse login(String email, String password) throws LoginException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getPassword().equals(password)) {
            UserRole userRole = user.getRole();
            String responseCode = "success";
            System.out.println(user.toString());
            return new LoginResponse(user, userRole, responseCode);
        } else {
            throw new InvalidCredentialsException("Invalid credentials");
        }
    } 


    @Override
    public Long saveUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getUserId();
    }
}
