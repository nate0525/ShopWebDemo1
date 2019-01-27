<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EBuy-- <c:if test="${not empty findProduct }">
${findProduct.pname }
</c:if></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"
	type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
</style>
</head>

<body>
	<!--描述：菜单栏-->
	<%@ include file="/jsp/head.jsp"%>
	<!--描述：导航条-->
	<%@ include file="/jsp/banner.jsp"%>
	<c:if test="${not empty findProduct }">
		<div class="container">
			<div class="row">
				<div
					style="border: 1px solid #e4e4e4; width: 930px; margin-bottom: 10px; margin: 0 auto; padding: 10px; margin-bottom: 10px;">
					<c:if test="${findProduct.is_hot==1 }">
						热门商品&nbsp;&nbsp;&gt;${category.cname }&nbsp;&nbsp;&gt;
					</c:if>
					<c:if test="${findProduct.is_hot==0 }">
						新品&nbsp;&nbsp;&gt;${category.cname }&nbsp;&nbsp;&gt;
					</c:if>
					${findProduct.pname }
				</div>

				<div style="margin: 0 auto; width: 950px;">
				
					<%-- <form id="myForm"
						action="${pageContext.request.contextPath}/CartServlet?method=addCartItemToCart&pid=${findProduct.pid }"
						method="post"> --%>
						
						<form id="myForm"
						action="${pageContext.request.contextPath}/ShoppingCarServlet?method=addProductToCarByPid&pid=${findProduct.pid }"
						method="post">
						
						
						<div class="col-md-6">
							<img style="opacity: 1; width: 400px; height: 350px;" title=""
								class="medium"
								src="${pageContext.request.contextPath}/${findProduct.pimage}">
						</div>

						<div class="col-md-6">
							<div>
								<strong>${findProduct.pname }</strong>
							</div>
							<div
								style="border-bottom: 1px dotted #dddddd; width: 350px; margin: 10px 0 10px 0;">
								<div>编号：${findProduct.pid }</div>
							</div>

							<div style="margin: 10px 0 10px 0;">
								商 城 价: <strong style="color: #ef0101;">￥：${findProduct.shop_price }元</strong>
								市 场 价：
								<del>￥：${findProduct.market_price }元</del>
							</div>

							<div style="margin: 10px 0 10px 0;">
								促销: <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)"
									style="background-color: #f07373;">限时抢购</a>
							</div>

							<div
								style="padding: 10px; border: 1px solid #e7dbb1; width: 330px; margin: 15px 0 10px 0;; background-color: #fffee6;"
								align="center">


								<div
									style="border-bottom: 1px solid #faeac7; margin-top: 20px; padding-left: 10px;">
									购买数量: <input id="quantity" name="quantity" value="1"
										maxlength="4" size="10" type="text">
								</div>

								<c:if test="${not empty userLoginOK }">
									<div style="margin: 20px 0 10px 0;; text-align: center;">
										<%--加入到购物车 --%>
										<a href="javascript:void(0)"> <input
											style="background: url('${pageContext.request.contextPath}/img/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;"
											value="加入购物车" id="btnId" type="button"></a> &nbsp;

										<c:if test="${not empty NotalreadyCollection }">
											<a
												href="${pageContext.request.contextPath}/ProductServlet?method=CollectionProduct&pid=${findProduct.pid}"
												style="border: 1px solid black;"><font size="3">收藏商品</font></a>
										</c:if>
										<c:if test="${not empty alreadyCollection }">
											<font size="3">已收藏</font>
										</c:if>
										<c:if test="${not empty MsgOK }">
											<font size="3">已收藏</font>
										</c:if>
									</div>
								</c:if>
								<c:if test="${ empty userLoginOK }">
									<a
										href="${pageContext.request.contextPath}/UserServlet?method=goUserLogin"
										target="_blank">登录</a>之后刷新可以放入购物车
								</c:if>

							</div>
						</div>
					</form>
				</div>
				<div class="clear"></div>
				<div style="width: 950px; margin: 0 auto;">
					<div
						style="background-color: #d3d3d3; width: 930px; padding: 10px 10px; margin: 10px 0 10px 0;">
						<strong>商品介绍</strong>
						<hr />
						<h4>${findProduct.pdesc }</h4>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<%@ include file="/jsp/footer.jsp"%>
</body>
<script>
	$(function() {
		$("#btnId").click(function() {
			var formObj = document.getElementById("myForm");
			//formObj.action=${pageContext.request.contextPath}+"/CartServlet?method=addCartItemToCart&pid="+${findProduct.pid };
			//formObj.method="post";
			formObj.submit();
		});
	});
</script>
</html>