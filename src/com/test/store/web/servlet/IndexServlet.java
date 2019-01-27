package com.test.store.web.servlet;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.test.store.domain.Category;
import com.test.store.domain.Product;
import com.test.store.service.CategoryService;
import com.test.store.service.ProductService;
import com.test.store.service.serviceImpl.CategoryServiceImpl;
import com.test.store.service.serviceImpl.ProductServiceImpl;
import com.test.store.web.base.BaseServlet;

public class IndexServlet extends BaseServlet {

	private static final long serialVersionUID = -8294606479546608323L;

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CategoryService categoryService = new CategoryServiceImpl();
		List<Category> findAllCategory = categoryService.findAllCategory();
		request.setAttribute("categoryList", findAllCategory);
		
		ProductService productService = new ProductServiceImpl();
		List<Product> findNewProductsList = productService.findNewProducts();
		List<Product> findHotProductsList = productService.findHotProducts();
		request.setAttribute("findNewProductsList", findNewProductsList);
		request.setAttribute("findHotProductsList", findHotProductsList);

		return "/jsp/index.jsp";

	}
	
	public String goIndexUI(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.sendRedirect("/WebProject/index.jsp");
		return null;
	}
	public String goAdminIndexUI(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.sendRedirect("/WebProject/admin/home.jsp");
		return null;
	}
	
	
}
