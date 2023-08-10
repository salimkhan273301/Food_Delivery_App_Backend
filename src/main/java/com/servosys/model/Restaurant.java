package com.servosys.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String address;
    private String phoneNumber;
    private String pincode;
    private String ownerName;
    @Column(unique=true)
    private String email;
    private String password;
    
    public Restaurant() {
    }

    
    // Add a constructor with a String argument
    public Restaurant(String restaurantId) {
        this.restaurantId = Long.valueOf(restaurantId);
    }

    public Restaurant(Long restaurantId, User user, String name, String address, String phoneNumber, String pincode, String ownerName, String email, String password) {
        this.restaurantId = restaurantId;
        this.user = user;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pincode = pincode;
        this.ownerName = ownerName;
        this.email = email;
        this.password = password;
    }

   
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters and setters for other fields

    @Override
    public String toString() {
        return "Restaurant [restaurantId=" + restaurantId + ", user=" + user + ", name=" + name + ", address=" + address
                + ", phoneNumber=" + phoneNumber + ", pincode=" + pincode + ", ownerName=" + ownerName + "]";
    }
}
