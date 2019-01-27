package com.test.store.web.servlet;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.store.domain.ShoppingCar;
import com.test.store.domain.ShoppingCarItem;
import com.test.store.domain.User;
import com.test.store.service.ShoppingCarService;
import com.test.store.service.serviceImpl.ShoppingCarServiceImpl;
import com.test.store.web.base.BaseServlet;

public class ShoppingCarServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	ShoppingCarService shoppingCarService = new ShoppingCarServiceImpl();

	public String addProductToCarByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {

		User user = (User) request.getSession().getAttribute("userLoginOK");

		int pid = Integer.parseInt(request.getParameter("pid"));

		int num = 0;
		try {
			num = Integer.parseInt(request.getParameter("quantity"));
		} catch (Exception e) {
			num = 1;
		}

		shoppingCarService.addProductToCarByPid(user.getUserID(), pid, num);

		if (request.getParameter("backuri") != null) {
			if (request.getParameter("backuri").equals("product_info")) {
				if (request.getParameter("NowPage") != null) {
					response.sendRedirect("/WebProject/ProductServlet?method=findMyCollection&num="
							+ Integer.parseInt(request.getParameter("NowPage")));
				} else {
					response.sendRedirect("/WebProject/ProductServlet?method=findMyCollection");
				}
			}
		} else {
			response.sendRedirect("/WebProject/ShoppingCarServlet?method=findShoppingCarProduct");
		}

		return null;

	}

	public String findShoppingCarProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		User user = (User) request.getSession().getAttribute("userLoginOK");

		List<ShoppingCarItem> list = shoppingCarService.findShoppingCarProduct(user.getUserID());

		double subTotal = 0;
		for (ShoppingCarItem item : list) {
			int quantity = item.getQuantity();
			double shop_price = item.getShop_price();
			subTotal += quantity * shop_price;
			item.setSubMoney(item.getQuantity() * item.getShop_price());
			item.setTotalMoney(subTotal);
		}

		DecimalFormat df = new DecimalFormat("######0.00");
		df.format(subTotal);

		request.setAttribute("list", list);
		request.setAttribute("subTotal", subTotal);

		return "/jsp/cart.jsp";
	}

	public String updateQuantity(HttpServletRequest request, HttpServletResponse response) throws Exception {

		User user = (User) request.getSession().getAttribute("userLoginOK");

		int pid = Integer.parseInt(request.getParameter("pid"));

		ShoppingCar car = shoppingCarService.findShoppingCarProductByPid(user.getUserID(), pid);

		String updateQuantity = request.getParameter("updateQuantity");

		if (updateQuantity.equals("up")) {
			car.setQuantity(car.getQuantity() + 1);
		} else if (updateQuantity.equals("down")) {
			if (car.getQuantity() <= 1) {
				response.sendRedirect("/WebProject/ShoppingCarServlet?method=findShoppingCarProduct");
				return null;
			}
			car.setQuantity(car.getQuantity() - 1);
		}

		shoppingCarService.UpdateShoppingCarProduct(car.getQuantity(), pid, user.getUserID());

		response.sendRedirect("/WebProject/ShoppingCarServlet?method=findShoppingCarProduct");

		return null;
	}

	public String delShoppingCarProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		User user = (User) request.getSession().getAttribute("userLoginOK");

		int pid = Integer.parseInt(request.getParameter("pid"));

		shoppingCarService.delShoppingCarProduct(pid, user.getUserID());

		response.sendRedirect("/WebProject/ShoppingCarServlet?method=findShoppingCarProduct");

		return null;
	}

	public String clearShoppingCarProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		User user = (User) request.getSession().getAttribute("userLoginOK");

		shoppingCarService.clearShoppingCarProduct(user.getUserID());

		response.sendRedirect("/WebProject/ShoppingCarServlet?method=findShoppingCarProduct");

		return null;
	}

}
