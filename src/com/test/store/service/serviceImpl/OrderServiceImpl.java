package com.test.store.service.serviceImpl;

import java.sql.Connection;
import java.util.List;

import com.test.store.dao.OrderDao;
import com.test.store.domain.Order;
import com.test.store.domain.Order2;
import com.test.store.domain.OrderItem;
import com.test.store.domain.PageModel;
import com.test.store.domain.User;
import com.test.store.service.OrderService;
import com.test.store.util.BeanFactory;
import com.test.store.util.JDBCUtils;

public class OrderServiceImpl implements OrderService {

	// Connection conn = null;不能放这句
	OrderDao orderdao = (OrderDao) BeanFactory.createObject("OrderDao");

	public void saveOrder(Order order) throws Exception {

		/*
		 * try { JDBCUtils.startTransaction(); OrderDao orderdao = new OrderDaoImpl();
		 * orderdao.saveOrder(order); for (OrderItem item : order.getList()) {
		 * orderdao.saveOrderItem(item); } JDBCUtils.commitAndClose(); } catch
		 * (Exception e) { JDBCUtils.rollbackAndClose(); }
		 */
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);

			orderdao.saveOrder(conn, order);
			for (OrderItem item : order.getList()) {
				orderdao.saveOrderItem(conn, item);
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
	}

	public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {

		int totalRecords = orderdao.getTotalRecords(user);

		PageModel pm = new PageModel(curNum, totalRecords, 5);

		List<?> list = orderdao.findMyOrderWithPage(user, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}

	/*public Order findOrderByOid(String oid) throws Exception {
		return orderdao.findOrderByOid(oid);
	}*/
	
	public Order2 findOrderByOid(String oid) throws Exception {
		return orderdao.findOrderByOid(oid);
	}
	
	public void updateOrder(Order2 order) throws Exception {

		orderdao.updateOrder(order);

	}

	public int delOrder(String oid) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);

			if (orderdao.delOrder(oid) == 1) {
				conn.commit();
				return 1;
			}

		} catch (Exception e) {
			conn.rollback();
		}
		return 0;

	}

	public PageModel findAllOrderWithPage(int curNum) throws Exception {

		int totalRecords = orderdao.countAllOrderWithPage();

		PageModel pm = new PageModel(curNum, totalRecords, 7);

		List<Order> list = orderdao.findAllOrderWithPage(pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		pm.setUrl("/AdminOrderServlet?method=findAllOrder");
		return pm;

	}

	public PageModel findAllOrderWithPage(int state, int curNum) throws Exception {

		int totalRecords = orderdao.countAllOrderByState(state);

		PageModel pm = new PageModel(curNum, totalRecords, 7);

		List<Order> list = orderdao.findAllOrderWithPage(state, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		if (state == 1) {
			pm.setUrl("/AdminOrderServlet?method=findAllOrder&state=1");
		} else if (state == 2) {
			pm.setUrl("/AdminOrderServlet?method=findAllOrder&state=2");
		} else if (state == 3) {
			pm.setUrl("/AdminOrderServlet?method=findAllOrder&state=3");
		} else if (state == 4) {
			pm.setUrl("/AdminOrderServlet?method=findAllOrder&state=4");
		}
		return pm;

	}

	public int countAllOrderWithPage() throws Exception {
		return orderdao.countAllOrderWithPage();
	}
	
	public int countAllOrderByState(int state) throws Exception{
		return orderdao.countAllOrderByState(state);
	}

}