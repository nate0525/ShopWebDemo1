package com.test.store.domain;

public class User {

	private String userID;

	private String username;

	private String password;

	private String sex;

	private String tel;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public User(String userID, String username, String password, String sex, String tel) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.tel = tel;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", sex=" + sex
				+ ", tel=" + tel + "]";
	}

}
