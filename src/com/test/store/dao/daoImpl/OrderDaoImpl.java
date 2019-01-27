package com.test.store.dao.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.test.store.dao.OrderDao;
import com.test.store.domain.Order;
import com.test.store.domain.Order2;
import com.test.store.domain.OrderItem;
import com.test.store.domain.Product;
import com.test.store.domain.User;
import com.test.store.util.JDBCUtils;

public class OrderDaoImpl implements OrderDao {

	public void saveOrder(Connection conn, Order order) throws Exception {
		String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner();
		Object[] params = { order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(),
				order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUserID() };
		qr.update(conn, sql, params);
	}

	public void saveOrderItem(Connection conn, OrderItem item) throws Exception {
		String sql = "INSERT INTO orderitem VALUES(?,?,?,?,?)";
		QueryRunner qr = new QueryRunner();
		Object[] params = { item.getItemid(), item.getQuantity(), item.getTotal(), item.getProduct().getPid(),
				item.getOrder().getOid() };
		qr.update(conn, sql, params);
	}

	public int getTotalRecords(User user) throws SQLException {

		String sql = "select count(*) from orders where uid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(), user.getUserID());
		return num.intValue();
	}

	public List findMyOrderWithPage(User user, int startIndex, int pageSize) throws Exception {

		String sql = "select * from orders where uid=? limit ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list = qr.query(sql, new BeanListHandler<Order>(Order.class), user.getUserID(), startIndex,
				pageSize);
		for (Order order : list) {
			String oid = order.getOid();
			sql = "SELECT * FROM orderitem o,product p WHERE o.pid=p.pid AND oid=?";
			List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(), oid);
			for (Map<String, Object> map : list02) {
				OrderItem orderitem = new OrderItem();
				Product product = new Product();
				// 1_创建时间类型的转换器
				DateConverter dt = new DateConverter();
				// 2_设置转换的格式
				dt.setPattern("yyyy-MM-dd");
				// 3_注册转换器
				ConvertUtils.register(dt, java.util.Date.class);
				BeanUtils.populate(orderitem, map);
				BeanUtils.populate(product, map);
				orderitem.setProduct(product);
				order.getList().add(orderitem);
			}
		}
		return list;
	}

	/*public Order findOrderByOid(String oid) throws Exception {

		String sql = "SELECT * FROM orders WHERE oid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		sql = "SELECT * FROM orderitem o,product p WHERE o.pid=p.pid AND oid=?";
		List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(), oid);
		for (Map<String, Object> map : list02) {
			OrderItem orderitem = new OrderItem();
			Product product = new Product();
			// 1_创建时间类型的转换器
			DateConverter dt = new DateConverter();
			// 2_设置转换的格式
			dt.setPattern("yyyy-MM-dd");
			// 3_注册转换器
			ConvertUtils.register(dt, java.util.Date.class);
			BeanUtils.populate(orderitem, map);
			BeanUtils.populate(product, map);
			orderitem.setProduct(product);
			order.getList().add(orderitem);
		}

		return order;
	}*/
	
	public Order2 findOrderByOid(String oid) throws Exception {

		String sql = "SELECT * FROM orders WHERE oid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Order2 order2 = qr.query(sql, new BeanHandler<Order2>(Order2.class), oid);
		sql = "SELECT * FROM orderitem o,product p WHERE o.pid=p.pid AND oid=?";
		List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(), oid);
		for (Map<String, Object> map : list02) {
			OrderItem orderitem = new OrderItem();
			Product product = new Product();
			// 1_创建时间类型的转换器
			DateConverter dt = new DateConverter();
			// 2_设置转换的格式
			dt.setPattern("yyyy-MM-dd");
			// 3_注册转换器
			ConvertUtils.register(dt, java.util.Date.class);
			BeanUtils.populate(orderitem, map);
			BeanUtils.populate(product, map);
			orderitem.setProduct(product);
			order2.getList().add(orderitem);
		}

		return order2;
	}

	public void updateOrder(Order2 order) throws Exception {

		String sql = "UPDATE orders SET ordertime=?,total=?,state=?,address=?,NAME=?,telephone=? WHERE oid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = { order.getOrdertime(), order.getTotal(), order.getState(), order.getAddress(),
				order.getName(), order.getTelephone(), order.getOid() };
		qr.update(sql, params);

	}

	public int delOrder(String oid) throws Exception {

		String sql1 = "DELETE FROM orderitem WHERE oid=?";
		QueryRunner qr1 = new QueryRunner(JDBCUtils.getDataSource());
		int update1 = qr1.update(sql1, oid);
		String sql2 = "DELETE FROM orders WHERE oid=?";
		QueryRunner qr2 = new QueryRunner(JDBCUtils.getDataSource());
		int update2 = qr2.update(sql2, oid);
		if (update1 == 1 && update2 == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public List<Order> findAllOrderWithPage(int startIndex, int pageSize) throws Exception {

		String sql = "select * from orders limit ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list = qr.query(sql, new BeanListHandler<Order>(Order.class), startIndex, pageSize);
		return list;
	}

	public List<Order> findAllOrderWithPage(int state, int startIndex, int pageSize) throws Exception {

		String sql = "SELECT * FROM orders WHERE state=? LIMIT ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list = qr.query(sql, new BeanListHandler<Order>(Order.class), state, startIndex, pageSize);
		return list;
	}

	public int countAllOrderWithPage() throws Exception {
		String sql = "SELECT COUNT(*) FROM orders";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler());
		return num.intValue();
	}
	
	public int countAllOrderByState(int state) throws Exception {
		String sql = "SELECT COUNT(*) FROM orders WHERE state=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(),state);
		return num.intValue();
	}
	
}
