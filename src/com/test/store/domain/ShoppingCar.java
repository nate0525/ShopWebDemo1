package com.test.store.domain;

public class ShoppingCar {

	private String userID;
	private int pid;
	private int quantity;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ShoppingCar(String userID, int pid, int quantity) {
		super();
		this.userID = userID;
		this.pid = pid;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ShoppingCar [userID=" + userID + ", pid=" + pid + ", quantity=" + quantity + "]";
	}

	public ShoppingCar() {
		super();
	}

}
