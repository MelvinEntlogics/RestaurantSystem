package com.entlogics.restaurantsystem.entity;

import java.util.List;
import java.util.Set;

public class Customer {

	private int customerId;

	private String customerName;

	private String customerAddress;

	private String customerPhone;
	
	private String customerEmail;
	
	private String customerStatus;

	private int customerSpent;

	private List<Restaurant> customerRestaurants;

	private Set<Order> customerOrders;

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

	public String getCustomerEmail() {
		return customerEmail;
	}
	
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public int getCustomerSpent() {
		return customerSpent;
	}

	public void setCustomerSpent(int customerSpent) {
		this.customerSpent = customerSpent;
	}

	public List<Restaurant> getCustomerRestaurants() {
		return customerRestaurants;
	}

	public void setCustomerRestaurants(List<Restaurant> customerRestaurants) {
		this.customerRestaurants = customerRestaurants;
	}

	public Set<Order> getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(Set<Order> customerOrders) {
		this.customerOrders = customerOrders;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", customerPhone=" + customerPhone + ", customerEmail=" + customerEmail
				+ ", customerStatus=" + customerStatus + ", customerSpent=" + customerSpent + "]";
	}

}
