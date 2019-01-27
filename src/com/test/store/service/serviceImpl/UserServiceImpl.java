package com.test.store.service.serviceImpl;

import java.sql.SQLException;
import com.test.store.dao.UserDao;
import com.test.store.domain.User;
import com.test.store.service.UserService;
import com.test.store.util.BeanFactory;

public class UserServiceImpl implements UserService {
	
	UserDao userDao = (UserDao) BeanFactory.createObject("UserDao");

	public int userRegister(User user) {
		int i = userDao.userRegister(user);
		return i;
	}

	public User userLogin(User user) throws SQLException {
		User user2 = userDao.userLogin(user);
		return user2;
	}
	
	public User findUserByUserID(String userID) throws SQLException{
		return userDao.findUserByUserID(userID);
	}

}
