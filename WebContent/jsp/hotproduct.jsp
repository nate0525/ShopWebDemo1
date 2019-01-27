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
		<div class="col-md-12">
			<h2>
				热门商品&nbsp;&nbsp;<img
					src="${pageContext.request.contextPath}/img/title2.jpg" />
			</h2>
		</div>
		<div class="col-md-2"
			style="border: 1px solid #E7E7E7; border-right: 0; padding: 0;">
			<img src="${pageContext.request.contextPath}/products/hao/big01.jpg"
				width="205" height="404" style="display: inline-block;" />
		</div>
		<div class="col-md-10">
			<div class="col-md-6"
				style="text-align: center; height: 200px; padding: 0px;">
				<a href="product_info.htm"> <img
					src="${pageContext.request.contextPath}/products/hao/middle01.jpg"
					width="516px" height="200px" style="display: inline-block;">
				</a>
			</div>

			<c:forEach items="${findHotProductsList }" var="p" begin="0" end="8">
				<div class="col-md-2 yes-right-border"
					style="text-align: center; height: 200px; padding: 10px 0px;">
					<a href="ProductServlet?method=ProductInfo&pid=${p.pid }" target="_blank"> <img
						src="${pageContext.request.contextPath}/${p.pimage}" width="130"
						height="130" style="display: inline-block;">
					</a>
					<p>
						<a href="ProductServlet?method=ProductInfo&pid=${p.pid }" style='color: #666' target="_blank">${p.pname }</a>
					</p>
					<p>
						<font color="#E4393C" style="font-size: 16px">&yen;${p.shop_price }</font>
					</p>
				</div>
			</c:forEach>

		</div>
	</div>
</body>
</html>