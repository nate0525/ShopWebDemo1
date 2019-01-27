<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提示页面</title>
<link href="${pageContext.request.contextPath}/css/userInfo.css"
	rel="stylesheet" type="text/css" media="all" />
</head>
<body>
	<c:if test="${not empty userRegisterOK }">
		<script language="javascript">
			setTimeout("location.href='/WebProject/jsp/login.jsp';", 1000);
		</script>
		<div class="loading">
			<div class="spinner-wrapper">
				<span class="spinner-text">注册成功，1秒后转到登录页面 </span>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty userRegisterNO }">
		<script language="javascript">
			setTimeout("location.href='/WebProject/jsp/register.jsp';",
					1000);
		</script>
		<div class="loading">
			<div class="spinner-wrapper">
				<span class="spinner-text">注册失败，1秒后转到注册页面 </span>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty userLoginOK }">
		<script language="javascript">
			setTimeout("location.href='/WebProject/index.jsp';", 500);
		</script>
		<div class="loading">
			<div class="spinner-wrapper">
				<span class="spinner-text">登陆成功，0.5秒后转到主页面 </span>
			</div>
		</div>
	</c:if>
</body>
</html>