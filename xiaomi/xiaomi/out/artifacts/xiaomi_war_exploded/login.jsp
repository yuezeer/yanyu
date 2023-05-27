<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/xiaomi/css/login.css">
<script type="text/javascript" src="/xiaomi/js/jquery.min.js"></script>

<title>登录</title>
</head>
<body>
		<!-- login -->
		<div class="top center">
			<div class="logo center">
				<a href="${pageContext.request.contextPath }/index.jsp" target="_blank"><img src="./image/mistore_logo.png" alt=""></a>
			</div>
		</div>
		<form class="form center" id="userLogin" >
		<div class="login">
			<div class="login_center">
				<div class="login_top">
					<div class="left fl">会员登录</div>
					<div class="right fr">您还不是我们的会员？<a href="/xiaomi/register.jsp" target="_self">立即注册</a></div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="login_main center">
					<div class="username">
						<div class="left fl">用户名:&nbsp;</div>
						<div class="right fl">
						<input class="shurukuang" type="text" name="username" id="username"  placeholder="请输入你的用户名"/>
						<label id="nameMsg"></label>
						</div>
					</div>
					<div class="username">
						<div class="left fl">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;</div>
						<div class="right fl">
						<input class="shurukuang" type="password" name="password"  id="password"  placeholder="请输入你的密码"/>
						</div>
					</div>
					<div class="username">
						<div class="left fl">验证码:&nbsp;</div>
						<div class="right fl"><input class="yanzhengma" name="vcode" id="vcode" type="text" placeholder="验证码"/>
						<img  id="pagecode" src="/xiaomi/code" style="cursor:pointer;width: 100px;height: 40px">
							<label id="checkMsg"></label>
						</div>
					</div>
					<div class="username">
						<div class="left fl">&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="right fl"><label id="checkMsg1"></label></div>
					</div>
					<div class="username">
						<input id="autoLogin" name="auto" type="checkbox" />&nbsp;&nbsp;两周以内自动登录
						<span id="autoLoginMsg"></span>
					</div>
					<div class="login_submit">
						<input class="submit" type="button" name="submit" value="立即登录" id="btn" >
					</div>
					<span style="color:red">${msg}</span>
				</div>
			</div>
		</div>
		</form>
		<footer>
			<div class="copyright">简体 | 繁体 | English | 常见问题</div>
			<div class="copyright">小米公司版权所有-京ICP备10046444-<img src="./image/ghs.png" alt="">京公网安备11010802020134号-京ICP证110507号</div>

		</footer>
	</body>
</html>
<%--导入layui--%>
<link rel="stylesheet" href="/xiaomi/js/layui/css/layui.css">
<script src="/xiaomi/js/layui/layui.js"></script>

<script>
    //2.点击验证码 跟新验证码
    $("#pagecode").click(function(){
        $("#pagecode").attr("src","/xiaomi/code?"+Math.random());
    });
	//异步验证用户信息，验证码是否正确
    // //{
    // 'username':$('#username').val(),
    //     'password':$('#password').val(),
    //     'code':$('vcode').val()
    // }
	$('#btn').click(function (){
		$.post('/xiaomi/user?method=login',$('#userLogin').serialize(),function (data){
            console.log(data);
		    if (!data.flag){
                layui.use('layer', function(){
                    let layer = layui.layer;
                    layer.alert(data.message, {icon: 2});
                });
            }else {
		        location.href="/xiaomi/index?method=banners";
            }
		},'json')
	})
	//验证是否激活成功
	if ('${activeMsg}'){
		layui.use('layer', function(){
			let layer = layui.layer;
			layer.alert("激活成功", {icon: 1});
		});
	}
</script>