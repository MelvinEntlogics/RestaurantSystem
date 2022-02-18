package com.entlogics.restaurantsystem.entity;

import java.util.List;

public class Dish {

	private int dishId;

	private String dishName;

	// Confirm with sir about order_items table Many to many or one to many
	private List<Restaurant> dishRestaurants;

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public List<Restaurant> getDishRestaurants() {
		return dishRestaurants;
	}

	public void setDishRestaurants(List<Restaurant> dishRestaurants) {
		this.dishRestaurants = dishRestaurants;
	}

	@Override
	public String toString() {
		return "Dish [dishId=" + dishId + ", dishName=" + dishName + "]";
	}

}
