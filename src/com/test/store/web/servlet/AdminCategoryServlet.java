package com.test.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.test.store.domain.Category;
import com.test.store.service.CategoryService;
import com.test.store.service.serviceImpl.CategoryServiceImpl;
import com.test.store.web.base.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	CategoryService categoryService = new CategoryServiceImpl();

	public String getCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Category> findAllCategory = categoryService.findAllCategory();

		request.setAttribute("findAllCategory", findAllCategory);

		return null;
	}

	public String findAllCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		getCategory(request, response);

		return "/admin/category/list.jsp";
	}

	public String addCategoryUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/admin/category/add.jsp";
	}

	public String addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int cid = categoryService.getMaxCid();

		String cname = request.getParameter("cname");

		int i = categoryService.AddCategory(cid, cname);

		if (i == 1) {
			request.setAttribute("msg", cname + "添加成功");
			getCategory(request, response);
			return "/admin/category/list.jsp";
		} else {
			request.setAttribute("msg", cname + "添加失败");
			return "/admin/category/add.jsp";
		}

	}

	public String delCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("cid");
		
		int cid=Integer.parseInt(id);
		
		Category category = categoryService.searchCategory(cid);

		int i = categoryService.delCategory(cid);

		getCategory(request, response);

		if (i == 1) {
			request.setAttribute("msg", category.getCname() + "删除成功");
			return "/admin/category/list.jsp";
		} else if (i == -1) {
			request.setAttribute("msg", category.getCname() + "删除失败,因为该分类拥有商品,请先删除该类下的所有商品再删除该类");
			return "/admin/category/list.jsp";
		} else {
			request.setAttribute("msg", category.getCname() + "删除失败");
			return "/admin/category/list.jsp";
		}

	}

	public String editCategoryUI(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String cid = request.getParameter("cid");

		Category category = categoryService.searchCategory(Integer.parseInt(cid));

		request.setAttribute("category", category);

		return "/admin/category/edit.jsp";
	}

	public String editCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		int i = categoryService.EditCategory(Integer.parseInt(cid), cname);
		if (i == 1) {
			request.setAttribute("msg", "编辑成功");
			getCategory(request, response);
			return "/admin/category/list.jsp";
		} else {
			request.setAttribute("msg", "编辑失败");
			return "/admin/category/edit.jsp";
		}

	}

}
