package com.servosys.responses;

import com.servosys.model.User;
import com.servosys.model.UserRole;

public class LoginResponse {
    private User user;
    private UserRole userType;
    private String responseCode;

    public LoginResponse(User user, UserRole userType, String responseCode) {
        this.user = user;
        this.userType = userType;
        this.responseCode = responseCode;
    }



	public LoginResponse() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserRole getUserType() {
		return userType;
	}

	public void setUserType(UserRole userType) {
		this.userType = userType;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}



	@Override
	public String toString() {
		return "LoginResponse [user=" + user + ", userType=" + userType + ", responseCode=" + responseCode + "]";
	}

    // Getters and setters
}
