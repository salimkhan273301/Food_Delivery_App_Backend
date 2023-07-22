package com.servosys.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

   // @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
   @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
 

    private User user;

    private String name;
    private String address;
    private String phoneNumber;
    private String pincode;

    // Additional fields
    private String email;
    private String password;

    public Customer(Long customerId, User user, String name, String address, String phoneNumber, String pincode, String email, String password) {
        this.customerId = customerId;
        this.user = user;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pincode = pincode;
        this.email = email;
        this.password = password;
    }

    public Customer() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    // Getters and setters for additional fields

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

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", user=" + user + ", name=" + name + ", address=" + address
                + ", phoneNumber=" + phoneNumber + ", pincode=" + pincode + "]";
    }
}
