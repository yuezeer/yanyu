<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>购物车</title>
<link href="/xiaomi/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/xiaomi/js/jquery.min.js"></script>
</head>
<body style="background-color:#f5f5f5">
<%@ include file="header.jsp"%>
<div class="container" style="background-color: white;">
	<div class="row" style="margin-left: 40px">
		<h3>我的购物车<small>温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</small></h3>
	</div>
	<div class="row" style="margin-top: 40px;">
		<div class="col-md-10 col-md-offset-1">
			<table class="table table-bordered">
 				<tr>
 					<th>
						<input type="checkbox" id="father" />
					</th>
 					<th>商品图片</th>
 					<th>商品名称</th>
 					<th>价格</th>
 					<th>数量</th>
 					<th>小计</th>
 					<th>操作</th>
 				</tr>
 				<%--<c:set value="0" var="sum"></c:set>--%>
 				<c:forEach items="${cartItems}" var="cartItem" varStatus="i">
	 				<tr>
	 					<td>
							<input class="son" id="son${cartItem.cid}" value="${cartItem.cid}" type="checkbox"  />
						</td>
	 					<td>
							<a href="#">
								<img src="${cartItem.goods.picture}"
								style="width: 60px;height: 60px" />
							</a>
						</td>
	 					<td>${cartItem.goods.name}</td>
	 					<td>${cartItem.goods.price}</td>
	 					<td width="100px">
		 					<div class="input-group">
		 						<span class="input-group-btn">
		 							<button class="btn btn-default" type="button"
											onclick="decr(${cartItem.cid},${cartItem.goods.price})">-</button>
		 						</span>
		 						<input oninput="changeNum($(this),${cartItem.cid},${cartItem.goods.price})" type="text" class="form-control" id="num_count${cartItem.cid}"
									   value="${cartItem.num}"  style="width:40px;margin-top:0">
		 						<span class="input-group-btn">
		 							<button onclick="incr(${cartItem.cid},${cartItem.goods.price})" class="btn btn-default" type="button">+</button>
		 						</span>
	 						</div>
	 					</td>
	 					<th id="thMoney${cartItem.cid}" class="sum">${cartItem.money}</th>
	 					<th>
	 						<button onclick="deleteCart(${cartItem.cid})" type="button" class="btn btn-danger">删除</button>
	 					</th>
	 				</tr>
 				</c:forEach>
			</table>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="pull-right" style="margin-right: 110px;">
			
	            <div>
	            	<a id="removeAllProduct" href="javascript:clearCart()" class="btn btn-default btn-lg">清空购物车</a>
	            	&nbsp;&nbsp;
	            	<a href="javascript:toOrderView()" class="btn  btn-danger btn-lg">结账</a>
	            	
	            </div>
	            <br><br>
	            <div style="margin-bottom: 20px;font-size: 14px;font-weight: bold">
					商品金额总计：<span style="color: red">￥：
					</span>
					<span id="total" class="text-danger">
						0
					</span>
	            </div>
		</div>
	</div>
</div>
	
    
<!-- 底部 -->
<%@ include file="footer.jsp"%>
<link rel="stylesheet" href="/xiaomi/js/layui/css/layui.css">
<script src="/xiaomi/js/layui/layui.js"></script>
</body>
</html>
<script>
	function deleteCart(cid){
		layer.confirm('确认删除该购物信息吗？', {
			btn: ['确定', '取消'] //可以无限个按钮
			,btn3: function(index, layero){
				//按钮【按钮三】的回调
			}
		}, function(index, layero){
			layer.close(index);
			//发送删除请求
			location.href = "/xiaomi/cart?method=deleteCartItem&cid="+cid;
		}, function(index){
			//按钮【按钮二】的回调
			layer.close(index);
		});
	}

	//全选、反选
	$('#father').bind('click',function () {
		$('.son').prop('checked',$(this).prop('checked'));

		if($(this).prop('checked')){
			total = 0;
			//更新总价
			$('.son').each(function (){
				let sub = parseFloat($('#thMoney' + $(this).val()).text());
				total += sub;
				$('#total').text(total);
			});
		}else{
			total = 0;
			$('#total').text(total);
		}
	});
	//son决定father是否勾中
	$('.son').each(function () {
		$(this).click(function (){
			//勾中son的长度
			let checkedLength = $('.son:checked').length;
			//所有son的长度
			let length = $('.son').length;
			if(checkedLength == length){
				$('#father').prop('checked',true);
			}else{
				$('#father').prop('checked',false);
			}
		});
	});

	//增加数量
	function incr(cid,price){
		let num = $('#num_count' + cid).val();
		$('#num_count' + cid).val(parseInt(num) + 1);
		refresh(cid,price);
		if($('#son' + cid).prop('checked')){
			total += price;
			$('#total').text(total);
		}
	}
	//减少数量
	function decr(cid,price){
		let num = $('#num_count' + cid).val();
		if(parseInt(num) > 1){
			$('#num_count' + cid).val(parseInt(num) - 1);
		}
		refresh(cid,price);
		if($('#son' + cid).prop('checked')){
			total -= price;
			$('#total').text(total);
		}
	}

	//手动输入商品数量
	function changeNum($this,cid,price){
		let num = parseInt($this.val());
		if(num <= 0 || num >= 10){
			$this.val("1");
		}
		//改变小结和总价
		$('#thMoney' + cid).text(num * price);

		//算出总价
		total = 0;
		$('.son:checked').each(function (){
			let sub = parseInt($('#thMoney' + $(this).val()).text());
			total += sub;
			$('#total').text(total);
		});
	}

	let total = 0;
	//更新小结
	function refresh(cid,price){
		let num = $('#num_count' + cid).val();
		//更新小结数据
		num = $('#num_count' + cid).val();
		$('#thMoney' + cid).text(num * price);

	}

	//获取勾中商品条目，算出总价
	$('.son').each(function (){
		$(this).click(function (){
			let sub = parseInt($('#thMoney' + $(this).val()).text());
			if($(this).prop('checked')){
				total += sub;
			}else{
				total -= sub;
			}
			$('#total').text(total);
		});
	});

	//清空购物车
	function clearCart(){
		layer.confirm('确认清空购物车吗？', {
			btn: ['确定', '取消'] //可以无限个按钮
			,btn3: function(index, layero){
				//按钮【按钮三】的回调
			}
		}, function(index, layero){
			layer.close(index);
			//发送删除请求
			location.href = "/xiaomi/cart?method=clearCart";
		}, function(index){
			layer.close(index);
			//按钮【按钮二】的回调
		});
	}

	//跳转到订单预览界面
	function toOrderView(){
		let cids = [];
		$('.son:checked').each(function () {
			cids.push($(this).val());
		});
		location.href = "/xiaomi/order?method=toOrderView&cids="+cids.join() + "&total="+$('#total').text();
	}
</script>