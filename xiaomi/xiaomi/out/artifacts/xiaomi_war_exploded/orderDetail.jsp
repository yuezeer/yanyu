<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单详情页</title>
<link href="/xiaomi/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/xiaomi/js/jquery.min.js"></script>
<script src="/xiaomi/js/qrCode.js"></script>
<script src="/xiaomi/js/bootstrap.js"></script>
</head>
<body>
<%--二维码窗口--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">二维码付款</h4>
			</div>
			<div class="modal-body">
				<div id="qrCode" style="margin:120px 200px;height: 230px"></div>
			</div>
		</div>
	</div>
</div>
<%@ include file="header.jsp" %>

<div class="panel panel-default" style="margin: 0 auto;width: 95%;">
	<div class="panel-heading">
	    <h3 class="panel-title"><span class="glyphicon glyphicon-equalizer"></span>&nbsp;&nbsp;订单详情</h3>
	</div>
	<div class="panel-body">
	<table cellpadding="0" cellspacing="0" align="center" width="100%" class="table table-striped table-bordered table-hover">

		<tr>
			<td>订单编号:</td>
			<td>${order.id}</td>
			<td>订单时间:</td>
			<td>${order.time}</td>
		</tr>
		<tr>
			<td>收件人:</td>
			<td>${order.address.receiver}</td>
			<td>联系电话:</td>
			<td>${order.address.phone}</td>
		</tr>
		<tr>
			<td>送货地址:</td>
			<td>${order.address.province.
			concat(order.address.city).concat(order.address.region)}
			${order.address.detail}</td>
			<td>总价:</td>
			<td>${order.money}</td>
		</tr>
		<tr>
			<td align="center">商品列表:</td>
			<td colspan="3">
				<table align="center" cellpadding="0" cellspacing="0" width="100%"  class="table table-striped table-bordered table-hover">
					<tr align="center"  class="info">
						<th>序号</th>
						<th>商品封面</th>
						<th>商品名称</th>
						<th>商品评分</th>
						<th>商品日期</th>
						<th>商品单价</th>
						<th>购买数量</th>
						<th>小计</th>
					</tr>
					<c:forEach items="${order.cartItems}" var="cartItem" varStatus="i">
						<tr align="center">
							<th>${i.count}</th>
							<th>
								<img src="${cartItem.goods.picture}" width="50px" height="50px">
							</th>
							<th>${cartItem.goods.name}</th>
							<th>${cartItem.goods.star}</th>
							<th>${cartItem.goods.pubdate}</th>
							<th>${cartItem.goods.price}</th>
							<th>${cartItem.num}</th>
							<th>${cartItem.money}</th>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="4" style="margin-right: 40px;">
				<a href="javaScript:void(0)" onclick="history.go(-1)" class="btn btn-danger btn-sm">返回订单列表</a>
				&nbsp;&nbsp;
				<c:if test="${order.status eq 1 }">
					<button type="button" onclick="javascript:createCode('${order.id}',${order.money})"
							data-toggle="modal" data-target="#myModal"
							class="btn btn-warning btn-sm">支付宝支付</button>
					&nbsp;&nbsp;
					<button type="button" onclick="location.href='/payWeixin.jsp'" class="btn btn-success btn-sm">微信支付</button>
				</c:if>
			</td>
		</tr>
	</table>
	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
<script>
	function createCode(id,money){
		location.href="/xiaomi/test?id="+id + "&money=" + money;
	// 	let qrcode = new QRCode("qrCode", {
	// 		text: "http://192.168.138.1/xiaomi/test?id="+id + "&money="+money,
	// 		width: 180,
	// 		height: 180,
	// 		colorDark : "#000000",
	// 		colorLight : "#ffffff",
	// 		correctLevel : QRCode.CorrectLevel.H
	// 	});
	}
</script>