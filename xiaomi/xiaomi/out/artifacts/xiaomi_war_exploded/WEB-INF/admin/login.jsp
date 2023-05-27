<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
	    <!-- Bootstrap -->
	    <link href="/xiaomi/css/bootstrap.min.css" rel="stylesheet">
	    <script src="/xiaomi/js/jquery.min.js"></script>
	    <script src="/xiaomi/js/bootstrap.min.js"></script>
		<title>登录</title>

	<script type="text/javascript">
		$(function(){
			$("#eye").click(function(){
				if($("#password").attr("type")=="password"){
					$("#password").attr("type","text");
					$("#eye").attr("class","glyphicon glyphicon-eye-close");
				}else{
					$("#password").attr("type","password");
					$("#eye").attr("class","glyphicon glyphicon-eye-open");
				}
			})
		})
	</script>

	<style>
		#main{
			position: absolute;
			width: 400px;
			height: 300px;
			left:50%;
			top:40%;
			margin-left: -200px;
			margin-top: -100px;
		}
	</style>
</head>
<body>
<div id="main" class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			后台登录
		</div>
	</div>
	<div class="panel-body">
		<div style="text-align: center;">
			<img src="/image/mistore_logo.png" alt="logo" width="30%" height="30%" />
		</div>
		<form action="/xiaomi/admin?method=adminLogin" method="post">
			<div class="form-group">
				<label>用户名:</label>
				<input type="text" name="username" id="" class="form-control" placeholder="请输入用户名"/>
			</div>
			<div class="form-group">
				<label>密&nbsp;&nbsp;&nbsp;码:</label>
				<div class="input-group">
					<input type="password" name="password" id="password" class="form-control"  placeholder="请输入密码"/>
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-eye-open" id="eye"></span>
					</span>
				</div>
			</div>
			<div class="form-group" style="text-align: center;">
				<input type="submit" value="登录" class="btn btn-primary">
				<input type="reset" value="重置" class="btn btn-default">
			</div>
		</form>
	</div>
</div>
</body>
</html>