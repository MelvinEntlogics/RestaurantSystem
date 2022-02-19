package com.entlogics.restaurantsystem.entity;

public class RestaurantCustomer {

	private int restaurantId;
	private int customerId;
	private String customerStatus;
	private String customerAddress;
	private String customerPhone;
	private int customerSpent;
	private String customerName;
	private String customerEmail;
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	@Override
	public String toString() {
		return "RestaurantCustomer [restaurantId=" + restaurantId + ", customerId=" + customerId + ", customerStatus="
				+ customerStatus + ", customerAddress=" + customerAddress + ", customerPhone=" + customerPhone
				+ ", customerSpent=" + customerSpent + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + "]";
	}
	

}
