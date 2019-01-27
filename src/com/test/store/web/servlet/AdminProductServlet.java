package com.test.store.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.test.store.domain.Category;
import com.test.store.domain.PageModel;
import com.test.store.domain.Product;

import com.test.store.service.CategoryService;
import com.test.store.service.ProductService;
import com.test.store.service.serviceImpl.CategoryServiceImpl;
import com.test.store.service.serviceImpl.ProductServiceImpl;

import com.test.store.util.UploadUtils;

import com.test.store.web.base.BaseServlet;

public class AdminProductServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	ProductService productService = new ProductServiceImpl();

	public String findAllPrductWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String curnum = request.getParameter("num");

		if (curnum == null) {
			curnum = "1";
		}

		PageModel pm = productService.findAllProductWithPage(Integer.parseInt(curnum));

		int i = productService.CountAllProductByPflag(0);

		request.setAttribute("countUpProduct", i);

		request.setAttribute("page", pm);

		return "/admin/product/list.jsp";
	}

	public String findLosePrductWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int curnum = Integer.parseInt(request.getParameter("num"));

		PageModel pm = productService.findLosePrductWithPage("pflag", 1, curnum);
		
		int i = productService.CountAllProductByPflag(1);

		request.setAttribute("countDownProduct", i);

		request.setAttribute("page", pm);

		return "/admin/product/pushDown_list.jsp";
	}

	public String PushUpProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("pid");

		int pid = Integer.parseInt(id);

		Product product = productService.findProduct(pid);

		product.setPflag(0);

		productService.UpdateProduct(pid, product);

		String nowPage = request.getParameter("nowPage");

		if (nowPage != null) {
			if (request.getParameter("backUri") == "adminlist") {
				response.sendRedirect("/WebProject/AdminProductServlet?method=findAllPrductWithPage&num=1");
			} else {
				response.sendRedirect("/WebProject/AdminProductServlet?method=findLosePrductWithPage&num="
						+ Integer.parseInt(nowPage));
			}
		} else {
			response.sendRedirect("/WebProject/AdminProductServlet?method=findLosePrductWithPage&num=1");
		}
		return null;

	}

	public String DropDownProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("pid");

		int pid = Integer.parseInt(id);

		Product product = productService.findProduct(pid);

		product.setPflag(1);

		productService.UpdateProduct(pid, product);

		String nowPage = request.getParameter("nowPage");

		if (nowPage != null) {
			response.sendRedirect(
					"/WebProject/AdminProductServlet?method=findAllPrductWithPage&num=" + Integer.parseInt(nowPage));
		} else {
			response.sendRedirect("/WebProject/AdminProductServlet?method=findAllPrductWithPage&num=1");
		}

		return null;
	}

	public String addProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CategoryService categoryService = new CategoryServiceImpl();

		List<Category> findAllCategory = categoryService.findAllCategory();

		request.setAttribute("findAllCategory", findAllCategory);

		Date date = new Date();

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

		String dateTime = ft.format(date);

		request.setAttribute("dateTime", dateTime);

		return "/admin/product/add.jsp";
	}

	public String addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 存储表单中数据
		Map<String, String> map = new HashMap<String, String>();
		Product product = new Product();
		try {

			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			List<FileItem> list = upload.parseRequest(request);

			for (FileItem item : list) {
				if (item.isFormField()) {

					// 1_创建时间类型的转换器
					DateConverter dt = new DateConverter();
					// 2_设置转换的格式
					dt.setPattern("yyyy-MM-dd");
					// 3_注册转换器
					ConvertUtils.register(dt, java.util.Date.class);

					map.put(item.getFieldName(), item.getString("utf-8"));
				} else if (item.getName() == null || item.getName() == "") {
					map.put("pimage", "/products/NotFind.jpg");
				} else {

					String oldFileName = item.getName();

					String newFileName = UploadUtils.getUUIDName(oldFileName);

					InputStream is = item.getInputStream();

					String realPath = getServletContext().getRealPath("/products/3/");

					String dir = UploadUtils.getDir(newFileName);

					String path = realPath + dir;

					File newDir = new File(path);
					if (!newDir.exists()) {
						newDir.mkdirs();
					}
					File finalFile = new File(newDir, newFileName);
					if (!finalFile.exists()) {
						finalFile.createNewFile();
					}

					OutputStream os = new FileOutputStream(finalFile);

					IOUtils.copy(is, os);

					IOUtils.closeQuietly(is);

					IOUtils.closeQuietly(os);

					map.put("pimage", "/products/3/" + dir + "/" + newFileName);

				}
			}
			BeanUtils.populate(product, map);
			product.setPid(productService.getMaxPid());
			productService.saveProduct(product);

			response.sendRedirect("/WebProject/AdminProductServlet?method=findAllPrductWithPage&num=1");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String delProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String str = request.getParameter("pid");

		int pid = Integer.parseInt(str);

		productService.delProduct(pid);

		// 重定向无法发送 删除成功,除非重新执行查询所有商品方法
		if (request.getParameter("flag") == null) {
			String nowPage = request.getParameter("nowPage");
			if (nowPage != null) {
				response.sendRedirect("/WebProject/AdminProductServlet?method=findAllPrductWithPage&num="
						+ Integer.parseInt(nowPage));
			} else {
				response.sendRedirect("/WebProject/AdminProductServlet?method=findAllPrductWithPage&num=1");
			}
		} else {
			String nowPage = request.getParameter("nowPage");
			if (nowPage != null) {
				response.sendRedirect("/WebProject/AdminProductServlet?method=findLosePrductWithPage&num="
						+ Integer.parseInt(nowPage));
			} else {
				response.sendRedirect("/WebProject/AdminProductServlet?method=findLosePrductWithPage&num=1");
			}
		}
		return null;
	}

	public String EditProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String backUri = request.getParameter("backUri");

		String NowPage = request.getParameter("NowPage");

		if (NowPage == null || NowPage == "") {
			NowPage = "1";
		}

		int pid = Integer.parseInt(request.getParameter("pid"));

		Product product = productService.findProduct(pid);

		CategoryService categoryService = new CategoryServiceImpl();

		List<Category> list = categoryService.findAllCategory();

		request.setAttribute("backUri", backUri);

		request.setAttribute("product", product);

		request.setAttribute("NowPage", NowPage);

		request.setAttribute("list", list);

		return "/admin/product/edit.jsp";
	}

	public String EditProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 存储表单中数据
		int pid = Integer.parseInt(request.getParameter("pid"));
		String num = request.getParameter("NowPage");
		String backUri = request.getParameter("backUri");
		Map<String, String> map = new HashMap<String, String>();
		Product product = new Product();
		try {

			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			List<FileItem> list = upload.parseRequest(request);

			for (FileItem item : list) {
				if (item.isFormField()) {

					// 1_创建时间类型的转换器
					DateConverter dt = new DateConverter();
					// 2_设置转换的格式
					dt.setPattern("yyyy-MM-dd");
					// 3_注册转换器
					ConvertUtils.register(dt, java.util.Date.class);

					map.put(item.getFieldName(), item.getString("utf-8"));
				} else if (item.getName() == null || item.getName() == "") {

					map.put("pimage", request.getParameter("oldPimage"));

				} else {

					String oldFileName = item.getName();

					String newFileName = UploadUtils.getUUIDName(oldFileName);

					InputStream is = item.getInputStream();

					String realPath = getServletContext().getRealPath("/products/3/");

					String dir = UploadUtils.getDir(newFileName);

					String path = realPath + dir;

					File newDir = new File(path);
					if (!newDir.exists()) {
						newDir.mkdirs();
					}
					File finalFile = new File(newDir, newFileName);
					if (!finalFile.exists()) {
						finalFile.createNewFile();
					}

					OutputStream os = new FileOutputStream(finalFile);

					IOUtils.copy(is, os);

					IOUtils.closeQuietly(is);

					IOUtils.closeQuietly(os);

					map.put("pimage", "/products/3/" + dir + "/" + newFileName);

				}
			}
			BeanUtils.populate(product, map);
			int i = productService.UpdateProduct(pid, product);
			if (num != null) {
				if (backUri.equals("list")) {
					response.sendRedirect("/WebProject/AdminProductServlet?method=findAllPrductWithPage&num=" + num);
				} else if (backUri.equals("dropdownlist")) {
					response.sendRedirect("/WebProject/AdminProductServlet?method=findLosePrductWithPage&num=" + num);
				}
			} else {
				if (backUri.equals("list")) {
					response.sendRedirect("/WebProject/AdminProductServlet?method=findAllPrductWithPage&num=" + num);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
