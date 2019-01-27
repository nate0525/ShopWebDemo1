package com.test.store.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import com.test.store.domain.Order;
import com.test.store.domain.Order2;
import com.test.store.domain.OrderItem;
import com.test.store.domain.User;

public interface OrderDao {

	void saveOrder(Connection conn, Order order) throws Exception;

	void saveOrderItem(Connection conn, OrderItem item) throws Exception;

	int getTotalRecords(User user) throws SQLException;

	List<?> findMyOrderWithPage(User user, int startIndex, int pageSize) throws Exception;

	//Order findOrderByOid(String oid) throws Exception;
	
	Order2 findOrderByOid(String oid) throws Exception;

	void updateOrder(Order2 order) throws Exception;

	int delOrder(String oid) throws Exception;

	List<Order> findAllOrderWithPage(int startIndex, int pageSize) throws Exception;

	List<Order> findAllOrderWithPage(int state, int startIndex, int pageSize) throws Exception;

	int countAllOrderWithPage() throws Exception;
	
	int countAllOrderByState(int state) throws Exception;

}
