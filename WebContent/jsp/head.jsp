<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<div class="col-md-4">
			<a
				href="${pageContext.request.contextPath}/IndexServlet?method=goAdminIndexUI"
				target="_blank"><img
				src="${pageContext.request.contextPath}/img/logo2.png" /></a>
		</div>
		<div class="col-md-5">
			<img src="${pageContext.request.contextPath}/img/header.png" />
		</div>
		<div class="col-md-3" style="padding-top: 20px">
			<ol class="list-inline">
				<c:if test="${ empty userLoginOK }">
					<li><a
						href="${pageContext.request.contextPath}/UserServlet?method=goUserLogin">登录</a></li>
					<li><a
						href="${pageContext.request.contextPath}/UserServlet?method=goUserRegister">注册</a></li>
				</c:if>
				<c:if test="${not empty userLoginOK }">
					<li>${userLoginOK.username },你好!</li>
					<li><a
						href="${pageContext.request.contextPath}/ProductServlet?method=findMyCollection">我的收藏</a></li>
					<li><a
						href="${pageContext.request.contextPath}/ShoppingCarServlet?method=findShoppingCarProduct">购物车</a></li>
					<li><a
						href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrdersWithPage&num=1">我的订单</a></li>
					<li><a
						href="${pageContext.request.contextPath}/UserServlet?method=LogOut">退出</a></li>
				</c:if>
			</ol>
		</div>
	</div>
</body>
</html>