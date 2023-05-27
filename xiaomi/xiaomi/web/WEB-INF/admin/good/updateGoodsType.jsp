<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/xiaomi/css/bootstrap.min.css" />
<script src="/xiaomi/js/jquery.min.js"></script>
<script src="/xiaomi/js/bootstrap.min.js"></script>
<title>添加商品种类</title>
<script>

</script>
</head>
<body>
<div style="width:98%;margin-left: 1%;">
	<div class="panel panel-default">
		<div class="panel-heading">
			修改商品种类
		</div>
		<div class="panel-body">
			<form action="/xiaomi/goodsType?method=updateGoodsType" method="post">
<%--				<div class="row">--%>
<%--					<div class="form-group form-inline">--%>
<%--						<span>所属种类</span>--%>
<%--						<select name="goodsParent">--%>
<%--							<option value="1">--请选择--</option>--%>
<%--							<c:forEach items="${goodsTypeList }" var="type">--%>
<%--								<c:if test="${type.level <=2}">--%>
<%--									<option value="${type.id }">${type.name }</option>--%>
<%--								</c:if>--%>
<%--							</c:forEach>--%>
<%--						</select>--%>
<%--					</div>--%>
<%--				</div>--%>
				<div class="row">
					<div class="form-group form-inline">
						<span>种类名称</span>
						<input type="hidden" name="id" value="${goodType.id}">
						<input type="text" name="name" class="form-control" value="${goodType.name}">
					</div>
				</div>
				<div class="row">
					<div class="btn-group" style="margin-left: 114px">
						<button type="reset" class="btn btn-danger btn-xs">清空</button>
						<button type="submit" class="btn btn-success btn-xs" style="margin-left:26px;">修改</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>