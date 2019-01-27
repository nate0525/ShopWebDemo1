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
		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="IndexServlet">首页</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="myUL">
					<%-- <c:forEach items="${categoryList }" var="p">
						<li><a href="#">${p.cname }</a></li>
					</c:forEach> --%>
				</ul>
				<form class="navbar-form navbar-right" role="search"
					action="${pageContext.request.contextPath}/ProductServlet?method=searchProduct"
					method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search"
							name="search">
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
				</form>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid --> </nav>
	</div>
</body>
<script>
	$(function() {

		var url = "/WebProject/CategoryServlet";
		var obj = {
			"method" : "findAllCategory"
		};
		$.post(url, obj, function(data) {
			//alert(data);

			$.each(data, function(i, obj) {
				var li = "<li><a href='ProductServlet?method=seebanner&see="
						+ obj.cid + "'>" + obj.cname + "</a></li>";
				$("#myUL").append(li);
			});

		}, "json");

	});
</script>
</html>