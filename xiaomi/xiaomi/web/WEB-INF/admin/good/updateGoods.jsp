<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/xiaomi/css/bootstrap.min.css" />
<script src="/xiaomi/js/jquery.min.js"></script>
<script src="/xiaomi/js/bootstrap.min.js"></script>
<script src="/xiaomi/js/laydate/laydate.js"></script>

<script src="/xiaomi/js/ajaxfileupload.js"></script>
<title>商品添加页面</title>
</head>
<body>
	<div class="row" style="margin-left: 20px;">
		<form action="/xiaomi/goods?method=updateGoods" method="post">
			<input type="hidden" name="picture" id="picture"/>
			<input type="hidden" name="id" value="${goods.id}" />
			<div>
				<h3>修改商品</h3>
			</div>
			<hr />
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group form-inline">
						<label>名称:</label>
						<input type="text" name="name" class="form-control" value="${goods.name}" />
					</div>
					
					<div class="form-group form-inline">
						<label>分类:</label>
						<select name="typeid" id="type" class="form-control">
							<%--<c:forEach items="${goodTypes}" var="goodType">
								<option value="${goodType.id}">${goodType.name}</option>
							</c:forEach>--%>
						</select>
					</div>
					<div class="form-group form-inline">
						<label>时间:</label>
						<input type="text" name="pubdate"
							    id="pubdate" readonly
							   class="form-control"  value="${goods.pubdate}"/>
					</div>
					<script>
						laydate.render({
							elem: '#pubdate',
							trigger:'click'
						});
					</script>
				</div>
				<div class="col-sm-6">
					<div class="form-group form-inline">
						<label>价格:</label>
						<input type="text" name="price" class="form-control" value="${goods.price}"/>
					</div>
					<div class="form-group form-inline">
						<label>评分:</label>
						<input type="text" name="star" class="form-control" value="${goods.star}"/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-10">
					<div class="form-group form-inline">
						<button type="button" class="layui-btn" id="test1">
							<i class="layui-icon">&#xe67c;</i>上传图片
						</button>
						<img id="img" src="${goods.picture}" style="display:none;width: 100px;height:100px" />
					</div>
					<div class="form-group ">
						<label>商品简介</label>
						<textarea  name="intro" class="form-control" rows="5" >${goods.intro}</textarea>
					</div>
					<div class="form-group form-inline">
						<input type="submit" value="修改" class="btn btn-primary" />
						<input type="reset" value="重置" class="btn btn-default" />
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
<script src="/xiaomi/js/layerJs/layer.js"></script>
<%--<script src="/js/ajaxfileupload.js"></script>--%>
<%--layui--%>
<link rel="stylesheet"
	  href="/xiaomi/js/layui/css/layui.css" />
<script src="/xiaomi/js/layui/layui.js"></script>
<script>
	//文档加载完毕后查询所有商品种类
	$.get('/xiaomi/goods?method=queryGoodsType',function (data) {
		//data:List<GoodType>
		for (let goodType of data){
			$('#type').append("<option value="+goodType.id+">"+goodType.name+"</option>");
		}
		$('#type').val(${goods.typeid})
	},'json');

	//使用layui完成异步上传
	//创建一个上传组件
	layui.use('upload', function(){
		let upload = layui.upload;

		//执行实例
		let uploadInst = upload.render({
			elem: '#test1' //绑定元素
			,url: '/xiaomi/goods?method=uploadPicture' //上传接口
			,done: function(data){
				alert(data.msg);
				//上传完毕回调
				$('#img').css('display','inline');
				$('#img').prop('src',"/xiaomi" + data.url);

				//把图片路径设置到隐藏域
				$('#picture').val(data.url);
			}
			,error: function(){
				//请求异常回调
			}
		});
	});
</script>