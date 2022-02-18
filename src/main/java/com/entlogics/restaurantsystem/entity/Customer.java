package com.entlogics.restaurantsystem.entity;

import java.util.List;

public class Customer {

	private int customerId;

	private String customerName;

	private String customerAddress;

	private String customerPhone;

	private List<Restaurant> customerRestaurants;

	private List<Order> customerOrders;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public List<Restaurant> getCustomerRestaurants() {
		return customerRestaurants;
	}

	public void setCustomerRestaurants(List<Restaurant> customerRestaurants) {
		this.customerRestaurants = customerRestaurants;
	}

	public List<Order> getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(List<Order> customerOrders) {
		this.customerOrders = customerOrders;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", customerPhone=" + customerPhone + "]";
	}

}
