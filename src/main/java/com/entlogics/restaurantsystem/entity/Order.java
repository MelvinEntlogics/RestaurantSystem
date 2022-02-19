package com.entlogics.restaurantsystem.entity;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class Order {

	private int orderId;

	private Integer customerId;

	private Integer waiterId;
	
	private Integer orderAmount;

	private Integer restaurantId;

	private Date orderDate;

	private String type;

	private List<Dish> orderItems;
	
	private List<OrderItems> orderItem;
	

	public List<OrderItems> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItems> orderItem) {
		this.orderItem = orderItem;
	}

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
	
	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public List<Dish> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Dish> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", waiterId=" + waiterId + ", orderAmount="
				+ orderAmount + ", restaurantId=" + restaurantId + ", orderDate=" + orderDate + ", type=" + type + "]";
	}

}
