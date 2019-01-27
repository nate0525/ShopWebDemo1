package com.test.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.test.store.domain.User;
import com.test.store.service.UserService;
import com.test.store.service.serviceImpl.UserServiceImpl;
import com.test.store.util.MyBeanUtils;
import com.test.store.util.UUIDUtils;
import com.test.store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	UserService UserService = new UserServiceImpl();

	public String UserRegister(HttpServletRequest request, HttpServletResponse response) throws SQLException {

		if (request.getParameter("username").trim().length() < 1 || request.getParameter("password").trim().length() < 1
				|| request.getParameter("tel").trim().length() < 1) {
			request.setAttribute("msg", "以下选项必须填写完整");
			return "jsp/register.jsp";
		}

		Map<String, String[]> map = request.getParameterMap();

		User user = new User();

		MyBeanUtils.populate(user, map);

		user.setUserID(UUIDUtils.getId());

		int i = UserService.userRegister(user);

		if (i == 1) {
			request.setAttribute("userRegisterOK", "1");
		} else if (i == 0) {
			request.setAttribute("userRegisterNO", "1");
		} else if (i == -1) {
			request.setAttribute("msg", "用户名已被注册");
			return "jsp/register.jsp";
		}

		return "jsp/userInfo.jsp";
	}

	public String UserLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		if (request.getParameter("username").trim().length() < 1
				|| request.getParameter("password").trim().length() < 1) {
			request.setAttribute("msg", "用户和密码不能为空");
			return "jsp/login.jsp";
		}

		Map<String, String[]> map = request.getParameterMap();

		User user = new User();

		MyBeanUtils.populate(user, map);

		User user2 = UserService.userLogin(user);
		
		if (user2 != null) {
			request.getSession().setAttribute("userLoginOK", user2);
		} else {
			request.setAttribute("msg", "用户或密码错误");
			return "jsp/login.jsp";
		}

		return "jsp/userInfo.jsp";
	}

	public String goUserLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		return "jsp/login.jsp";
	}

	public String goUserRegister(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		return "jsp/register.jsp";
	}

	public String goIndex(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		return "/index.jsp";
	}

	public void LogOut(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.getSession().removeAttribute("userLoginOK");
		response.sendRedirect("/WebProject/index.jsp");
	}

	
	
}
