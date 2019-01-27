package com.test.store.service;

import com.test.store.domain.Order;
import com.test.store.domain.Order2;
import com.test.store.domain.PageModel;
import com.test.store.domain.User;

public interface OrderService {

	void saveOrder(Order order) throws Exception;

	PageModel findMyOrdersWithPage(User user, int curNum) throws Exception;

	//Order findOrderByOid(String oid) throws Exception;
	
	Order2 findOrderByOid(String oid) throws Exception;
	
	void updateOrder(Order2 order) throws Exception;

	int delOrder(String oid) throws Exception;

	PageModel findAllOrderWithPage(int curNum) throws Exception;

	PageModel findAllOrderWithPage(int state, int curNum) throws Exception;

	int countAllOrderWithPage() throws Exception;
	
	int countAllOrderByState(int state) throws Exception;

}
