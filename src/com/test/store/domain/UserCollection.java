package com.test.store.domain;

import java.util.Date;

public class UserCollection {

	private String userID;
	private int pid;
	private Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserCollection(String userID, int pid, Date date) {
		super();
		this.userID = userID;
		this.pid = pid;
		this.date = date;
	}

	public UserCollection() {
		super();
	}

	@Override
	public String toString() {
		return "UserCollection [userID=" + userID + ", pid=" + pid + ", date=" + date + "]";
	}

}
