package com.servosys.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne//(cascade=CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String name;
    private String description;
    private double price;
    private String food_image_url = "./assets/chickenWings.jpg";
    
	public MenuItem() {
		super();
	}
	public MenuItem(Long itemId, Restaurant restaurant, String name, String description, double price, String food_image_url) {
		super();
		this.itemId = itemId;
		this.restaurant = restaurant;
		this.name = name;
		this.description = description;
		this.price = price;
		this.food_image_url = food_image_url;
		}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getFood_image_url() {
		return food_image_url;
	}
	public void setFood_image_url(String food_image_url) {
		this.food_image_url = food_image_url;
	}
	@Override
	public String toString() {
		return "MenuItem [itemId=" + itemId + ", restaurant=" + restaurant + ", name=" + name + ", description="
				+ description + ", price=" + price + ", food_image_url=" + food_image_url + "]";
	}


    // Getters and setters
}

