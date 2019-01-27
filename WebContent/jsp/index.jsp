<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
</head>

<body>
	<div class="container-fluid">
		<!--描述：菜单栏-->
		<%@ include file="/jsp/head.jsp"%>
		<!--描述：导航条-->
		<%@ include file="/jsp/banner.jsp"%>
		<!--描述：轮播条-->
		<%@ include file="/jsp/bannerPic.jsp"%>
		<!--描述：新商品显示-->
		<%@ include file="/jsp/newproduct.jsp"%>
		<!--描述：广告部分-->
		<%@ include file="/jsp/adpage.jsp"%>
		<!--描述：热门商品显示-->
		<%@ include file="/jsp/hotproduct.jsp"%>
		<!--描述：页脚部分 -->
		<%@ include file="/jsp/footer.jsp"%>
	</div>
</body>

</html>