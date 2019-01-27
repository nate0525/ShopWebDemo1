package com.test.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.test.store.domain.Cart;
import com.test.store.domain.CartItem;
import com.test.store.domain.Product;
import com.test.store.service.ProductService;
import com.test.store.service.serviceImpl.ProductServiceImpl;
import com.test.store.web.base.BaseServlet;

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();

	public String addCartItemToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Cart cart = (Cart) request.getSession().getAttribute("cart");

		if (null == cart) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		String pid = request.getParameter("pid");
		int num = 0;
		try {
			num = Integer.parseInt(request.getParameter("quantity"));
		} catch (Exception e) {
			num = 1;
		}

		Product product = productService.findProduct(Integer.parseInt(pid));

		CartItem cartItem = new CartItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);

		cart.addCartItemToCar(cartItem);

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
			response.sendRedirect("/WebProject/jsp/cart.jsp");
		}
		return null;

	}

	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pid = request.getParameter("pid");

		Cart cart = (Cart) request.getSession().getAttribute("cart");

		cart.removeCartItem(pid);

		response.sendRedirect("/WebProject/jsp/cart.jsp");

		return null;
	}

	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Cart cart = (Cart) request.getSession().getAttribute("cart");

		cart.clearCart();

		response.sendRedirect("/WebProject/jsp/cart.jsp");

		return null;
	}
}
