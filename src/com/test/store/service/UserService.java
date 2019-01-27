package com.test.store.service;

import java.sql.SQLException;

import com.test.store.domain.User;

public interface UserService {

	public int userRegister(User user);

	public User userLogin(User user) throws SQLException;
	
	User findUserByUserID(String userID) throws SQLException;

}
