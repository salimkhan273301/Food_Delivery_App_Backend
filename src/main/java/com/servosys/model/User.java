package com.servosys.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

  
    private String password;
    @Column(unique=true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

	public User(Long userId, String password, String email, UserRole role) {
		super();
		this.userId = userId;
		
		this.password = password;
		this.email = email;
		this.role = role;
	}
	
	


	public User() {
		super();
	}




	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ",  password=" + password + ", email=" + email
				+ ", role=" + role + "]";
	}

    // Getters and setters
}

