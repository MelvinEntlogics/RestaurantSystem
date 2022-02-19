package com.entlogics.restaurantsystem.entity;

public class OrderItems {

	private int itemId;
	private int itemQuantity;
	private Integer itemAmouunt;
	private Integer orderID;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public int getItemAmouunt() {
		return itemAmouunt;
	}
	public void setItemAmouunt(int itemAmouunt) {
		this.itemAmouunt = itemAmouunt;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	@Override
	public String toString() {
		return "OrderItems [itemId=" + itemId + ", itemQuantity=" + itemQuantity + ", itemAmouunt=" + itemAmouunt
				+ ", orderID=" + orderID + "]";
	}

}
