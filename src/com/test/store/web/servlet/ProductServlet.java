package com.test.store.web.servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.store.dao.ProductDao;
import com.test.store.dao.daoImpl.ProductDaoImpl;
import com.test.store.domain.Category;
import com.test.store.domain.PageModel;
import com.test.store.domain.Product;
import com.test.store.domain.User;
import com.test.store.domain.UserCollection;
import com.test.store.service.CategoryService;
import com.test.store.service.ProductService;
import com.test.store.service.serviceImpl.CategoryServiceImpl;
import com.test.store.service.serviceImpl.ProductServiceImpl;
import com.test.store.util.PageUtils;
import com.test.store.web.base.BaseServlet;

public class ProductServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	ProductService productService = new ProductServiceImpl();

	CategoryService categoryService = new CategoryServiceImpl();

	public void findProductInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pid = request.getParameter("pid");

		Product findProduct = productService.findProduct(Integer.parseInt(pid));

		if (findProduct == null) {
			request.setAttribute("nullProduct", "无发现");
		} else {

			Category category = categoryService.searchCategory(findProduct.getCid());

			request.setAttribute("category", category);

			request.setAttribute("findProduct", findProduct);
		}
	}

	public String AdminfindProductInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			findProductInfo(request, response);
			if (request.getParameter("backUri").equals("adminlist")) {
				PageModel pm = new PageModel(1, 0, 5);
				request.setAttribute("page", pm);
				return "/admin/product/list.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("nullProduct", "无发现");
			return "/admin/product/list.jsp";
		}

		return null;

	}

	public String ProductInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

		findProductInfo(request, response);

		User user = (User) request.getSession().getAttribute("userLoginOK");

		if (user != null) {
			int pid = Integer.parseInt(request.getParameter("pid"));
			int i = productService.CollectionProductOrNot(user.getUserID(), pid);
			if (i == 1) {
				// 已经收藏
				request.setAttribute("alreadyCollection", "1");
			} else if (i == 0) {
				// 没有收藏
				request.setAttribute("NotalreadyCollection", "1");
			}
		}

		return "/jsp/product_info.jsp";
	}

	public String CollectionProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pid = request.getParameter("pid");

		User user = (User) request.getSession().getAttribute("userLoginOK");

		Date date = new Date();

		int i = productService.CollectionProduct(user.getUserID(), Integer.parseInt(pid), date);
		if (i == 1) {
			request.setAttribute("MsgOK", "OK");
		} else if (i == 0) {
			request.setAttribute("MsgNO", "NO");
		}

		findProductInfo(request, response);

		return "/jsp/product_info.jsp";

	}

	public String seebanner(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String cid = request.getParameter("see");

		int countAll = 0;

		PageUtils findAllProductUtils = new PageUtils();

		findAllProductUtils.setPageSize(12);

		int currentPage = 1;

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		findAllProductUtils.setCurrentPage(currentPage);

		countAll = productService.countAll(cid);

		findAllProductUtils.setTotalCount(countAll);

		List<Product> findAllProduct = productService.findAllProduct(cid, findAllProductUtils);

		Category category = categoryService.searchCategory(Integer.parseInt(cid));

		request.setAttribute("category", category);

		request.setAttribute("countAll", countAll);

		if (countAll > 0) {

			request.setAttribute("findAllProductUtils", findAllProductUtils);

			request.setAttribute("findAllProduct", findAllProduct);

		} else {

			request.setAttribute("NullMsg", "暂无该类商品");

		}
		return "/jsp/product_list.jsp";
	}

	public String searchProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		String search = request.getParameter("search");

		int countAll = productService.CountSearchProduct(search);

		List<Product> findSearchProduct = productService.findSearchProduct(search);

		request.setAttribute("countAll", countAll);

		request.setAttribute("searchInfo", "关于"+search);

		if (countAll > 0) {

			request.setAttribute("findSearchProduct", findSearchProduct);

		} else {

			request.setAttribute("NullMsg", "暂无该类商品");

		}
		return "/jsp/product_list.jsp";

	}

	public String findMyCollection(HttpServletRequest request, HttpServletResponse response) throws Exception {

		User user = (User) request.getSession().getAttribute("userLoginOK");

		String num = request.getParameter("num");

		if (num == null || num.equals(null)) {
			num = "1";
		}

		int curNum = Integer.parseInt(num);

		PageModel pm = productService.findCollectionByUserIDWithPage(user.getUserID(), curNum);

		List<UserCollection> list = pm.getList();

		List<Product> list2 = new ArrayList<Product>();
		for (UserCollection userCollection : list) {
			int pid = userCollection.getPid();
			ProductDao productDao = new ProductDaoImpl();
			Product product2 = productDao.findProduct(pid);
			list2.add(product2);
		}

		request.setAttribute("page", pm);

		request.setAttribute("list2", list2);

		return "/jsp/product_list.jsp";
	}

	public String delMyCollection(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int pid = Integer.parseInt(request.getParameter("pid"));
		productService.delCollectionByPid(pid);
		if (request.getParameter("NowPage") != null) {
			response.sendRedirect("/WebProject/ProductServlet?method=findMyCollection&num="
					+ Integer.parseInt(request.getParameter("NowPage")));
		} else {
			response.sendRedirect("/WebProject/ProductServlet?method=findMyCollection");
		}
		return null;
	}

}
