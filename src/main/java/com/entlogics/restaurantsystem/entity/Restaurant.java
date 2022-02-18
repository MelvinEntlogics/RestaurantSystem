package com.entlogics.restaurantsystem.entity;

import java.util.List;

public class Restaurant {
	private int restaurantId;

	private String restaurantName;

	private String restaurantAddress;

	private String restaurantEmail;

	private String restaurantPhone;

	private int restaurantRating;

	private String restaurantType;

	private List<Waiter> restaurantWaiters;

	private List<Order> restaurantOrders;

	private List<Customer> restaurantCustomers;

	private List<Dish> restaurantDishes;

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getRestaurantEmail() {
		return restaurantEmail;
	}

	public void setRestaurantEmail(String restaurantEmail) {
		this.restaurantEmail = restaurantEmail;
	}

	public String getRestaurantPhone() {
		return restaurantPhone;
	}

	public void setRestaurantPhone(String restaurantPhone) {
		this.restaurantPhone = restaurantPhone;
	}

	public int getRestaurantRating() {
		return restaurantRating;
	}

	public void setRestaurantRating(int restaurantRating) {
		this.restaurantRating = restaurantRating;
	}

	public String getRestaurantType() {
		return restaurantType;
	}

	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}

	public List<Waiter> getRestaurantWaiters() {
		return restaurantWaiters;
	}

	public void setRestaurantWaiters(List<Waiter> restaurantWaiters) {
		this.restaurantWaiters = restaurantWaiters;
	}

	public List<Order> getRestaurantOrders() {
		return restaurantOrders;
	}

	public void setRestaurantOrders(List<Order> restaurantOrders) {
		this.restaurantOrders = restaurantOrders;
	}

	public List<Customer> getRestaurantCustomers() {
		return restaurantCustomers;
	}

	public void setRestaurantCustomers(List<Customer> restaurantCustomers) {
		this.restaurantCustomers = restaurantCustomers;
	}

	public List<Dish> getRestaurantDishes() {
		return restaurantDishes;
	}

	public void setRestaurantDishes(List<Dish> restaurantDishes) {
		this.restaurantDishes = restaurantDishes;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", restaurantAddress=" + restaurantAddress + ", restaurantEmail=" + restaurantEmail
				+ ", restaurantPhone=" + restaurantPhone + ", restaurantRating=" + restaurantRating
				+ ", restaurantType=" + restaurantType + "]";
	}

}
