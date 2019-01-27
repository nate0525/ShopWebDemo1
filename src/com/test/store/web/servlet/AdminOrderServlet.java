package com.test.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.store.domain.Order2;
import com.test.store.domain.PageModel;
import com.test.store.service.OrderService;
import com.test.store.service.serviceImpl.OrderServiceImpl;
import com.test.store.web.base.BaseServlet;

import net.sf.json.JSONArray;

public class AdminOrderServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderServiceImpl();

	public String findAllOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String num = request.getParameter("num");

		if (num == null || num.equals(null)) {
			num = "1";
		}

		int curNum = Integer.parseInt(num);

		String str = request.getParameter("state");
		
		PageModel pm = null;

		if (str == null || num.equals(null)) {
			pm = orderService.findAllOrderWithPage(curNum);
		} else {
			int state2 = Integer.parseInt(str);
			pm = orderService.findAllOrderWithPage(state2, curNum);
		}
		
		int i = pm.getTotalRecords();
		if(i>0) {
			request.setAttribute("total", i);
		}else {
			request.setAttribute("NullMsg", "暂无该类订单项");
		}
		request.setAttribute("page", pm);

		return "/admin/order/list.jsp";
	}
	
	public String findOrderByOidWithAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String oid = request.getParameter("id");
		
		Order2 order = orderService.findOrderByOid(oid);
		
		String jsonStr = JSONArray.fromObject(order.getList()).toString();
		
		response.setContentType("application/json;charset=utf-8");
		
		response.getWriter().println(jsonStr);
		
		return null;
	}
	
	public String updateOrderbyOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String oid = request.getParameter("oid");
		
		Order2 order = orderService.findOrderByOid(oid);
		
		order.setState(3);
		
		orderService.updateOrder(order);
		
		response.sendRedirect("/WebProject/AdminOrderServlet?method=findAllOrder&state=3");
		
		return null;
	}

}
