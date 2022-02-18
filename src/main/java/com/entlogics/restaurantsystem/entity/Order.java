package com.entlogics.restaurantsystem.entity;

import java.sql.Date;
import java.util.List;

public class Order {

	private int orderId;

	private int customerId;

	private int waiterId;

	private int restaurantId;

	private Date orderDate;

	private String type;

	private List<Dish> orderItems;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getWaiterId() {
		return waiterId;
	}

	public void setWaiterId(int waiterId) {
		this.waiterId = waiterId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Dish> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Dish> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", waiterId=" + waiterId + ", restaurantId="
				+ restaurantId + ", orderDate=" + orderDate + ", type=" + type + "]";
	}

}
