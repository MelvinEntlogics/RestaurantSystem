package com.entlogics.restaurantsystem.entity;

public class RestaurantDish {

	private int dishID;
	private int dishPrice;
	private String dishName;
	public int getDishID() {
		return dishID;
	}
	public void setDishID(int dishID) {
		this.dishID = dishID;
	}
	public int getDishPrice() {
		return dishPrice;
	}
	public void setDishPrice(int dishPrice) {
		this.dishPrice = dishPrice;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	@Override
	public String toString() {
		return "RestauantDish [dishID=" + dishID + ", dishPrice=" + dishPrice + ", dishName=" + dishName + "]";
	}

}
