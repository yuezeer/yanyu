<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单预览页面</title>
<link href="/xiaomi/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/xiaomi/js/jquery.min.js"></script>
</head>
<body style="background-color:#f5f5f5">
<%@ include file="header.jsp"%>
<div class="container" style="background-color: white;">
	<div class="row" style="margin-left: 40px">
		<h3>订单预览<small>温馨提示：请添加你要邮递到的地址</small></h3>
	</div>
	<div class="row" style="margin-top: 40px;">
		<div class="col-md-10 col-md-offset-1">
			<table class="table table-bordered">
 				<tr>
 					<th>序号</th>
					<th>商品图片</th>
 					<th>商品名称</th>
 					<th>价格</th>
 					<th>数量</th>
 					<th>小计</th>
 				</tr>
 				<c:forEach items="${cartItems}" var="cartItem"
						   varStatus="i">
					<input type="hidden" value="${cartItem.cid}" class="cid" />
	 				<tr>
	 					<td>${i.count}</td>
						<td>
							<a href="/GoodServlet?method=toDetail&id=${cartItem.goods.id}">
								<img src="${cartItem.goods.picture}"
									 style="width: 60px;height: 60px" />
							</a>
						</td>
	 					<td>${cartItem.goods.name}</td>
	 					<td>${cartItem.goods.price}</td>
	 					<td>${cartItem.num}</td>
	 					<td>${cartItem.money}</td>
	 				</tr>
 				</c:forEach>
 				<tr>
 				 <td colspan="6">
 				 	<h5>收货地址</h5>
 				 	<select id="address" style="width:60%" class="form-control">

 				 	</select><br />
					 <a href="/xiaomi/order?method=toAddAddress">添加收货地址</a>
 				 </td>
 				</tr>
			</table>
		</div>
	</div>
	<hr>
	<div class="row">
		<div style="margin-left: 40px;">	  
	            <h4>商品金额总计：<span id="total" class="text-danger"><b>￥&nbsp;&nbsp;${totalMoney}</b></span></h4>
		</div>
	</div>
	<div class="row pull-right" style="margin-right: 40px;">
		 <div style="margin-bottom: 20px;">
	            <button  id="btn_add" class="btn  btn-danger btn-lg"
						 type="button" onclick="subOrder()">提交订单</button>
	     </div>
	</div>
</div>
<!-- 底部 -->
<%@ include file="footer.jsp"%>

<script>
	//收货地址
	$.get('/xiaomi/address?method=addressList',function (data){
		//data:List<Address>
		for (let address of data){
			$('#address').append("<option value="+address.id+">" +
					address.province + address.city + address.region
					+ " " + address.detail +" " + address.receiver + "(收)" +
					"</option>");
		}
	},'json');

	//提交订单
	function subOrder(){
		let total = '${totalMoney}';
		let cids = '${cids}';
		location.href = "/xiaomi/order?method=addOrder" +
				"&money="+total + "&cids="+cids+"&aid="+$('#address').val();
	}
</script>

</body>
</html>