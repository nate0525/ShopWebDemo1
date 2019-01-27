package com.test.store.dao;

import java.sql.SQLException;

import com.test.store.domain.User;

public interface UserDao {
	public int userRegister(User user);

	public User userLogin(User user) throws SQLException;
	
	User findUserByUserID(String userID) throws SQLException;
}
