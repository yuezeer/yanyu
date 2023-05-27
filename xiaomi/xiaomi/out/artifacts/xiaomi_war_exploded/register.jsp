<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<link href="/xiaomi/css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript" src="/xiaomi/js/jquery.min.js"></script>
		
<link rel="stylesheet" type="text/css" href="css/login.css">
<title>注册</title>
</head>
<body>
	<div class="regist">
		<div class="regist_center">
			<div class="regist_top">
				<div class="left fl"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;会员注册</div>
				<div class="right fr">
					<a href="index.jsp" target="_black">小米商城</a>
				</div>
				<div class="clear"></div>
				<div class="xian center"></div>
			</div>
			<div class="center-block" style="margin-top: 80px;">
				<form class="form-horizontal" action="/xiaomi/user?method=register" method="post">

					<div class="form-group">
						<label class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="text" id="username" name="username" class="form-control col-sm-10"
								placeholder="Username" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span class="help-block " id="usernameMsg">请输入6位以上字符</span></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="password" id="password" name="password"
								class="form-control col-sm-10" placeholder="Password" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="helpBlock" class="help-block ">请输入6位以上字符</span></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="password" id="againPassword" class="form-control col-sm-10"
								placeholder="Password Again" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="helpBlock" class="help-block ">两次密码要输入一致哦</span></p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="text" id="email" name="email" class="form-control col-sm-10"
								placeholder="Email" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="helpBlock" class="help-block ">填写正确邮箱格式</span></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">性别</label>
						<div class="col-sm-8" style="width: 40%">
							<label class="radio-inline"> <input type="radio"
								name="gender" value="男"> 男
							</label> <label class="radio-inline"> <input type="radio"
								name="gender" value="女"> 女
							</label>
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="helpBlock" class="help-block ">你是帅哥 还是美女</span></p>
						</div>
					</div>
					<hr>
					<div class="form-group">
						<div class="col-sm-7 col-sm-push-2">
							<input id="registerBtn" type="submit" value="注册" class="btn btn-primary  btn-lg" style="width: 200px;" /> &nbsp; &nbsp;
							<input type="reset" value="重置" class="btn btn-default  btn-lg" style="width: 200px;"  />
						</div>
					</div>
					<div>${session.registerMsg}</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>
<%--导入layui--%>
<link rel="stylesheet" href="/xiaomi/js/layui/css/layui.css">
<script src="/xiaomi/js/layui/layui.js"></script>
<script>
	//验证用户名格式是否正确
	// 1、用户名只能为英文字母，数字，下划线或者短横线组成,，并且用户名长度为 6~16位。
	let regexp1 = /^[a-zA-Z0-9_-]{6,16}$/;   // {6,16}  中间不要有空格
	$('#username').change(function (){
		if (!regexp1.test($(this).val())) {
			layui.use('layer', function(){
				let layer = layui.layer;
				layer.alert('用户名格式不正确！用户名只能为英文字母，数字，' +
						'下划线或者短横线组成,，并且用户名长度为 6~16位', {icon: 2});
			});
		}
	})
	//验证用户密码格式
	//6-16位字母和数字组合
	let regexp2 = /^[a-zA-Z]{6,16}$/
	$('#password').change(function (){
		if (!regexp2.test($(this).val())) {
			layui.use('layer', function(){
				let layer = layui.layer;
				layer.alert('用户密码格式不正确！6-16位字母', {icon: 2});
			});
		}
	})
	//验证邮箱格式
	let regexp3 = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/
	$('#email').change(function (){
		if (!regexp3.test($(this).val())) {
			layui.use('layer', function(){
				let layer = layui.layer;
				layer.alert('邮箱格式不正确', {icon: 2});
			});
		}
	})
	//异步验证用户名是否重复
	$('#username').change(function (){
		$.get('/xiaomi/user?method=repeatRegister',{username:$(this).val()},function (data){
			if (data.flag){
				layui.use('layer', function(){
					let layer = layui.layer;
					layer.alert(data.message, {icon: 2});
				});
			}
		},"json");
	})
	//验证两次密码是否输入一致
	$('#againPassword').change(function (){
		console.log($(this).val() != $('#password').val())
		if ($(this).val() != $('#password').val()){
			layui.use('layer', function(){
				let layer = layui.layer;
				layer.alert('两次密码输入不一致！', {icon: 2});
			});
		}
	});

</script>