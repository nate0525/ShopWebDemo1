<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>

<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/AdminProductServlet?method=addProductUI";
	}
</script>
</HEAD>
<body>
	<br>

	<table cellSpacing="1" cellPadding="0" width="100%" align="center"
		bgColor="#f5fafe" border="0">
		<TBODY>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colspan="2"><strong>商品列表</strong>
				</TD>
			</tr>
			<tr>
				<td class="ta_01" align="left">

					<form
						action="${pageContext.request.contextPath}/ProductServlet?method=AdminfindProductInfo&backUri=adminlist"
						method="post">
						<input type="text" name="pid" /><input type="submit"
							value="搜索商品ID" /><c:if test="${not empty countUpProduct }">&nbsp;共${countUpProduct }个商品</c:if>
					</form>
				</td>
				<td class="ta_01" align="right">
					<button type="button" id="add" name="add" value="添加"
						class="button_add" onclick="addProduct()">
						&#28155;&#21152;</button>

				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan="2">
					<form id="Form1" name="Form1"
						action="${pageContext.request.contextPath}/user/list.jsp"
						method="post">

						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="3%">商品ID</td>
								<td align="center" width="17%">商品图片</td>
								<td align="center" width="17%">商品名称</td>
								<td align="center" width="17%">商品价格</td>
								<td align="center" width="17%">是否热门</td>
								<td width="7%" align="center">编辑</td>
								<c:if test="${findProduct.pflag==0||empty findProduct }">
									<td width="7%" align="center">下架</td>
								</c:if>
								<c:if test="${findProduct.pflag==1 }">
									<td width="7%" align="center">上架</td>
								</c:if>
								<td width="7%" align="center">删除</td>
							</tr>
							<c:forEach items="${page.list }" var="p">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="3%">${p.pid}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><img width="90" height="90"
										src="${ pageContext.request.contextPath }/${p.pimage}"></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${ p.pname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${ p.shop_price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><c:if test="${p.is_hot==1}">是</c:if> <c:if
											test="${p.is_hot==0}">否</c:if></td>
									<td align="center" style="HEIGHT: 22px"><a
										href="${pageContext.request.contextPath}/AdminProductServlet?method=EditProductUI&backUri=list&pid=${p.pid}&NowPage=${page.currentPageNum}">
											<img
											src="${pageContext.request.contextPath}/img/admin/i_edit.gif"
											border="0" style="CURSOR: hand">
									</a></td>
									<td align="center" style="HEIGHT: 22px">
										<%--下架 pushdown --%> <a href="javascript:;" id=${p.pid }
										class="dropdown"> <img
											src="${pageContext.request.contextPath}/img/admin/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a>
									</td>
									<td align="center" style="HEIGHT: 22px">
										<%--删除  --%> <a href="javascript:;" id=${p.pid }
										class="delete"> <img
											src="${pageContext.request.contextPath}/img/admin/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${not empty findProduct }">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="3%">${findProduct.pid}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><img width="90" height="90"
										src="${ pageContext.request.contextPath }/${findProduct.pimage}"></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${ findProduct.pname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${ findProduct.shop_price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><c:if test="${findProduct.is_hot==1}">是</c:if>
										<c:if test="${findProduct.is_hot==0}">否</c:if></td>


									<td align="center" style="HEIGHT: 22px"><a
										href="${pageContext.request.contextPath}/AdminProductServlet?method=EditProductUI&backUri=list&pid=${findProduct.pid}">
											<img
											src="${pageContext.request.contextPath}/img/admin/i_edit.gif"
											border="0" style="CURSOR: hand">
									</a></td>

									<c:if test="${findProduct.pflag==0 }">
										<td align="center" style="HEIGHT: 22px">
											<%--下架 pushdown --%> <a href="javascript:;"
											id=${findProduct.pid } class="dropdown"> <img
												src="${pageContext.request.contextPath}/img/admin/i_del.gif"
												width="16" height="16" border="0" style="CURSOR: hand">
										</a>
										</td>
									</c:if>

									<c:if test="${findProduct.pflag==1 }">
										<td align="center" style="HEIGHT: 22px"><a
											href="javascript:;" id=${findProduct.pid } class="pushUp"> <img
												src="${pageContext.request.contextPath}/img/admin/i_edit.gif"
												border="0" style="CURSOR: hand"></a></td>
									</c:if>
									<td align="center" style="HEIGHT: 22px">
										<%--删除  --%> <a href="javascript:;" id=${findProduct.pid }
										class="delete"> <img
											src="${pageContext.request.contextPath}/img/admin/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a>
									</td>
								</tr>
							</c:if>
							<c:if test="${not empty nullProduct }">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="3%" colspan="8">${nullProduct }</td>
								</tr>
							</c:if>
						</table>
					</form>
				</td>
			</tr>
		</TBODY>
	</table>
	<c:if test="${ empty findProduct&&empty nullProduct }">
		<%@include file="/jsp/pageFile.jsp"%>
	</c:if>
</body>
<script>
	$(function() {
		$(".delete")
				.click(
						function() {
							if (confirm("确认删除?")) {
								var pid = this.id;
								window.location.href = "/WebProject/AdminProductServlet?method=delProduct&nowPage="+ ${page.currentPageNum}+"&pid=" + pid;
							}
						});
	});
	$(function() {
		$(".pushUp")
				.click(
						function() {
							if (confirm("确定上架?")) {
								var id = this.id;
								window.location.href = "/WebProject/AdminProductServlet?method=PushUpProductByPid&backUri=adminlist&nowPage="+ ${page.currentPageNum}+"&pid=" + id;
							}
						});
	});
	$(function() {
		$(".dropdown")
				.click(
						function() {
							if (confirm("确认下架?")) {
								var pid = this.id;
								window.location.href = "/WebProject/AdminProductServlet?method=DropDownProduct&nowPage="+ ${page.currentPageNum}+"&pid=" + pid;
							}
						});
	});
</script>
</HTML>

