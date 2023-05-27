<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/xiaomi/css/bootstrap.min.css" />
<title>购物车</title>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
<hr>
	<div class="row" style="width: 30%;margin: 0 auto;padding-top: 20px">
			<img src="/image/cart.png" style="vertical-align: middle">
			<h4 style="display: inline-block;
			position: relative;top: -80px;left: 150px">
				您的购物车还是空的，
				<a href="/xiaomi/index?method=banners"
				   class="btn btn-primary">前去购物</a>&nbsp;&nbsp;
			</h4>

	</div>
	
</div>

<%@ include file="footer.jsp" %>
</body>
</html>