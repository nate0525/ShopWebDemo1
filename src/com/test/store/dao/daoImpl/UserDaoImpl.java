package com.test.store.dao.daoImpl;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.test.store.dao.UserDao;
import com.test.store.domain.User;
import com.test.store.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

	public int userRegister(User user) {

		try {
			String sql = "INSERT INTO t_user VALUES(?,?,?,?,?)";
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			Object[] params = { user.getUserID(), user.getUsername(), user.getPassword(), user.getSex(),
					user.getTel() };
			int i = qr.update(sql, params);
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			String ex = e.getMessage();
			if (ex.contains("PRIMARY")) {
				return -1;
			}
		}
		return 0;
	}

	public User userLogin(User user) throws SQLException {

		String sql = "SELECT * FROM t_user WHERE username=? && PASSWORD=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());

	}
	
	public User findUserByUserID(String userID) throws SQLException {
		
		String sql="select * from t_user where userID=?";
		
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		
		User user = qr.query(sql, new BeanHandler<User>(User.class),userID);
		
		return user;
		
	}
	
}