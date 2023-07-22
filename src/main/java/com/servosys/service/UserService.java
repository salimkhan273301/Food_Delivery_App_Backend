package com.servosys.service;

import com.servosys.exception.LoginException;
import com.servosys.model.User;
import com.servosys.responses.LoginResponse;

public interface UserService {
	LoginResponse login(String username, String password) throws LoginException;

	Long saveUser(User user);

	
}
