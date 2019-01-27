package com.test.store.dao.daoImpl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.test.store.dao.ShoppingCarDao;
import com.test.store.domain.ShoppingCar;
import com.test.store.domain.ShoppingCarItem;
import com.test.store.util.JDBCUtils;

public class ShoppingCarDaoImpl implements ShoppingCarDao {

	public int addProductToCarByPid(String userID, int pid, int quantity) throws Exception {

		int i = 0;
		String sql = "SELECT * FROM shoppingcar WHERE userID=?&&pid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		ShoppingCar shoppingCar = qr.query(sql, new BeanHandler<ShoppingCar>(ShoppingCar.class), userID, pid);
		if (shoppingCar != null) {
			sql = "UPDATE shoppingcar SET quantity=? WHERE pid=?";
			i = qr.update(sql, quantity + shoppingCar.getQuantity(), pid);
		} else {
			sql = "INSERT INTO shoppingcar VALUES(?,?,?)";
			Object[] params = { userID, pid, quantity };
			i = qr.update(sql, params);
		}
		return i;

	}

	public List<ShoppingCarItem> findShoppingCarProduct(String userID) throws Exception {

		String sql = "SELECT * FROM product p , shoppingcar s WHERE p.pid=s.pid AND userID=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<ShoppingCarItem> list = qr.query(sql, new BeanListHandler<ShoppingCarItem>(ShoppingCarItem.class), userID);

		return list;

	}

	public ShoppingCar findShoppingCarProductByPid(String userID, int pid) throws Exception {

		String sql = "SELECT * FROM shoppingcar WHERE userID=? && pid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		ShoppingCar shoppingCar = qr.query(sql, new BeanHandler<ShoppingCar>(ShoppingCar.class), userID, pid);
		return shoppingCar;

	}

	public int UpdateShoppingCarProduct(int quantity, int pid, String userID) throws Exception {

		String sql = "UPDATE shoppingcar SET quantity=? WHERE pid=? && userID=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.update(sql, quantity, pid, userID);

	}

	public int delShoppingCarProduct(int pid, String userID) throws Exception {

		String sql = "DELETE FROM shoppingcar WHERE pid=?&&userID=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.update(sql, pid, userID);

	}

	public int clearShoppingCarProduct(String userID) throws Exception {

		String sql = "DELETE FROM shoppingcar WHERE userID=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.update(sql, userID);

	}

}
