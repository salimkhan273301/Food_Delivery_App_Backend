package com.servosys.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.servosys.exception.LoginException;
import com.servosys.responses.LoginResponse;
import com.servosys.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/api/login")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody Map<String, String> loginData) throws InterruptedException {
        try {
        	String email = loginData.get("email");
        	String password = loginData.get("password");
        		Thread.sleep(3000);
            LoginResponse response = userService.login(email, password);
            return ResponseEntity.ok(response);
        } catch (LoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(null, null,"fail"));
        }
    }
}
