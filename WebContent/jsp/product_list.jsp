<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EBuy</title>
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
	width: 100%;
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

	<div class="row" style="width: 1300px; margin: 0 auto;">
		<div class="col-md-12">
			<ol class="breadcrumb">
			
				<c:if test="${not empty page }">我的收藏列表---</c:if>
				
				<c:if test="${not empty category }">${category.cname }---</c:if>
				<li>${searchInfo }共有${countAll }${page.totalRecords }个商品</li>
				
				<c:if test="${not empty NullMsg }">
					<br />
					<b>${NullMsg }</b>
				</c:if>
				<c:if test="${page.totalRecords==0 }">
					<br />
					<b>你还没有收藏任何商品</b>
				</c:if>
			</ol>
		</div>

		<c:forEach items="${findAllProduct }" var="p" begin="0" end="11">
			<div class="col-md-2" align="center" style="height: 270px">
				<a href="ProductServlet?method=ProductInfo&pid=${p.pid }"
					target="_blank"> <img
					src="${pageContext.request.contextPath}/${p.pimage}" width="170"
					height="170" style="display: inline-block;">
				</a>
				<p>
					<a href="ProductServlet?method=ProductInfo&pid=${p.pid }"
						target="_blank" style='color: green'> ${p.pname}</a>
				</p>
				<p>
					<font color="#FF0000">商城价：&yen;${p.shop_price}</font>
				</p>
			</div>
		</c:forEach>

		<c:forEach items="${findSearchProduct }" var="p">
			<div class="col-md-2" align="center" style="height: 270px">
				<a href="ProductServlet?method=ProductInfo&pid=${p.pid }"
					target="_blank"> <img
					src="${pageContext.request.contextPath}/${p.pimage}" width="170"
					height="170" style="display: inline-block;">
				</a>
				<p>
					<a href="ProductServlet?method=ProductInfo&pid=${p.pid }"
						target="_blank" style='color: green'> ${p.pname}</a>
				</p>
				<p>
					<font color="#FF0000">商城价：&yen;${p.shop_price}</font>
				</p>
			</div>
		</c:forEach>



		<!-- pagemodel -->
		<c:forEach items="${list2 }" var="p">
			<c:if test="${not empty p.pid }">
				<div class="col-md-2" align="center" style="height: 310px">
					<div style="height: 250px;">
						<a href="ProductServlet?method=ProductInfo&pid=${p.pid }"
							target="_blank"> <img
							src="${pageContext.request.contextPath}/${p.pimage}" width="170"
							height="170" style="display: inline-block;">
						</a>
						<p>
							<a href="ProductServlet?method=ProductInfo&pid=${p.pid }"
								target="_blank" style='color: green'> ${p.pname}</a>
						</p>
						<p>
							<font color="#FF0000">商城价：&yen;${p.shop_price}</font>
						</p>
					</div>
					<p>
						<a href="javascript:;" id=${p.pid } class="addCart"><input
							type="button" value="添加到购物车" /></a> <a href="javascript:;"
							id=${p.pid } class="delete"><input type="button" value="删除" /></a>
					</p>
				</div>
			</c:if>
		</c:forEach>



	</div>

	<c:if test="${not empty findAllProductUtils }">
		<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
			<ul class="pagination" style="text-align: center; margin-top: 10px;">
				<li><a
					href="${pageContext.request.contextPath}/ProductServlet?method=seebanner&currentPage=${findAllProductUtils.currentPage-1}&see=${category.cid}"
					<c:if test="${findAllProductUtils.currentPage==1}">style="color:#ccc;" onclick="javascript:return false;"</c:if>
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
				<c:forEach begin="1" step="1"
					end="${findAllProductUtils.totalPage }" var="pg">
					<li><a
						href="${pageContext.request.contextPath}/ProductServlet?method=seebanner&currentPage=${pg}&see=${category.cid}">${pg }</a></li>
				</c:forEach>
				<li><a
					href="${pageContext.request.contextPath}/ProductServlet?method=seebanner&currentPage=${findAllProductUtils.currentPage+1}&see=${category.cid}"
					<c:if test="${findAllProductUtils.currentPage==findAllProductUtils.totalPage}">style="color:#ccc;" onclick="javascript:return false;"</c:if>
					aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
				<li><span aria-hidden="true">第${findAllProductUtils.currentPage}页
						共${findAllProductUtils.totalPage }页</span></li>
			</ul>
		</div>
	</c:if>
	<c:if test="${not empty page }">
		<%@ include file="/jsp/pageFile.jsp"%>
	</c:if>

	<%@ include file="/jsp/footer.jsp"%>

</body>
<script>
	$(function() {
		$(".addCart")
				.click(
						function() {
							var id = this.id;
							window.location.href = "/WebProject/ShoppingCarServlet?method=addProductToCarByPid&NowPage="+ ${page.currentPageNum}+"&backuri=product_info&pid=" + id;
							alert("添加完成");
						});
	});
	$(function() {
		$(".delete")
				.click(
						function() {
							if (confirm("确认删除?")) {
								var id = this.id;
								window.location.href = "/WebProject/ProductServlet?method=delMyCollection&NowPage="+ ${page.currentPageNum}+"&pid=" + id;
							}
						});
	});
</script>
</html>