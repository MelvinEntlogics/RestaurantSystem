package com.entlogics.restaurantsystem.entity;

import java.util.List;

public class Waiter {
	
	private int waiterId;

	private String waiterName;

	private Integer waiterSalary;

	private Integer restaurantId;

	private List<Order> waiterOrders;

	public int getWaiterId() {
		return waiterId;
	}

	public void setWaiterId(int waiterId) {
		this.waiterId = waiterId;
	}

	public String getWaiterName() {
		return waiterName;
	}

	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getWaiterSalary() {
		return waiterSalary;
	}

	public void setWaiterSalary(int waiterSalary) {
		this.waiterSalary = waiterSalary;
	}

	public List<Order> getWaiterOrders() {
		return waiterOrders;
	}

	public void setWaiterOrders(List<Order> waiterOrders) {
		this.waiterOrders = waiterOrders;
	}

	@Override
	public String toString() {
		return "Waiter [waiterId=" + waiterId + ", waiterName=" + waiterName + ", waiterSalary=" + waiterSalary
				+ ", restaurantId=" + restaurantId + "]";
	}

}
