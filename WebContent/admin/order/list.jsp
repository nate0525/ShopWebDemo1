<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
</HEAD>
<body>
	<br>

	<table cellSpacing="1" cellPadding="0" width="100%" align="center"
		bgColor="#f5fafe" border="0">
		<TBODY>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单列表</strong>
				</TD>
			</tr>
			<tr>
				<td class="ta_01" align="left">
					<form
						action="${pageContext.request.contextPath}/OrderServlet?method=findOrderByOid&backUri=adminorderlist"
						method="post">
						<input type="text" name="oid" /><input type="submit"
							value="搜索订单ID" />
						<c:if test="${not empty total }">
					共有${total }条记录
					</c:if>
						<c:if test="${not empty NullMsg }">
				${NullMsg }
				</c:if>
						<c:if test="${not empty Erreo }">
				${Erreo }
				</c:if>
					</form>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">
					<form id="Form1" name="Form1"
						action="${pageContext.request.contextPath}/user/list.jsp"
						method="post">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="2%">序号</td>
								<td align="center" width="10%">订单编号</td>
								<c:if test="${not empty order }">
									<td align="center" width="5%">下单用户</td>
								</c:if>
								<td align="center" width="10%">下单时间</td>
								<td align="center" width="6%">订单金额</td>
								<td align="center" width="8%">收货人</td>
								<td align="center" width="8%">收货人电话</td>
								<td align="center" width="17%">收货地址</td>
								<td align="center" width="6%">订单状态</td>
								<td align="center" width="50%">订单详情</td>
							</tr>
							<c:forEach items="${page.list }" var="p" varStatus="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="1%">${status.count }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${p.oid }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${p.ordertime }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%">${p.total }￥</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%"><c:if test="${empty p.name }">无填写</c:if>${p.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><c:if test="${empty p.telephone }">无填写</c:if>${p.telephone }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><c:if test="${empty p.address }">无填写</c:if>${p.address }</td>

									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="1%"><c:if test="${p.state==1 }">未付款</c:if> <c:if
											test="${p.state==2 }">
											<a
												href="${pageContext.request.contextPath}/AdminOrderServlet?method=updateOrderbyOid&oid=${p.oid}">可发货</a>
										</c:if> <c:if test="${p.state==3 }">已发货</c:if> <c:if
											test="${p.state==4 }">订单完成</c:if></td>
									<td align="center" style="HEIGHT: 22px"><input
										type="button" value="订单详情" id="${p.oid}" class="myClass" />
										<table>
											<tr></tr>
										</table></td>

								</tr>
							</c:forEach>

							<c:if test="${not empty order }">

								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%">1</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${order.oid }</td>
									<c:if test="${not empty order }">
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="5%">${user.username }</td>
									</c:if>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${order.ordertime }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%">${order.total }￥</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%"><c:if test="${empty order.name }">无填写</c:if>${order.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><c:if test="${empty order.telephone }">无填写</c:if>${order.telephone }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><c:if test="${empty order.address }">无填写</c:if>${order.address }</td>

									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="1%"><c:if test="${order.state==1 }">未付款</c:if> <c:if
											test="${order.state==2 }">
											<a
												href="${pageContext.request.contextPath}/AdminOrderServlet?method=updateOrderbyOid&oid=${order.oid}">可发货</a>
										</c:if> <c:if test="${order.state==3 }">已发货</c:if> <c:if
											test="${order.state==4 }">订单完成</c:if></td>
									<td align="center" style="HEIGHT: 22px"><input
										type="button" value="订单详情" id="${order.oid}" class="myClass" />
										<table>
											<tr></tr>
										</table></td>

								</tr>

							</c:if>

						</table>
					</form>
				</td>
			</tr>
			<tr align="center">
				<td colspan="7"></td>
			</tr>
		</TBODY>
	</table>
	<c:if test="${ empty order&&empty Erreo }">
		<%@ include file="/jsp/pageFile.jsp"%>
	</c:if>
</body>
<script>
	$(function() {
		$(".myClass")
				.click(
						function() {

							var id = this.id;

							var txt = this.value;

							var $tb = $(this).next();

							if (txt == "订单详情") {

								var url = "/WebProject/AdminOrderServlet"
								var obj = {
									"method" : "findOrderByOidWithAjax",
									"id" : id
								};

								$
										.post(
												url,
												obj,
												function(data) {//alert(data);//清空数据					
													$tb.html("");
													var tr = "<tr><td>商品</td><td>名称</td><td>单价</td><td>数量</td></tr>";
													$tb.append(tr);
													$
															.each(
																	data,
																	function(i,
																			obj) {
																		var td = "<tr><td><img src='/WebProject/"+obj.product.pimage+"' width='50px'/></td><td>"
																				+ obj.product.pname
																				+ "</td><td>"
																				+ obj.product.shop_price
																				+ "</td><td>"
																				+ obj.quantity
																				+ "</td></tr>";
																		$tb
																				.append(td);
																	})
												}, "json");
								this.value = "关闭订单"
							} else {
								this.value = "订单详情";
								$tb.html("");
							}

						});

	});
</script>
</HTML>

