<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/xiaomi/css/bootstrap.min.css"/>
    <script src="/xiaomi/js/jquery.min.js"></script>
    <script src="/xiaomi/js/bootstrap.min.js"></script>
    <script src="/xiaomi/js/laydate/laydate.js"></script>
    <title>商品列表</title>

</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                商品列表
            </div>
            <div class="panel-body">
                <div class="row">
                    <form action="/xiaomi/goods?method=showGoods" method="post">
                    <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
                        <div class="form-group form-inline">
                            <span>商品名称</span>
                            <input type="text" name="name1" class="form-control"
                                   placeholder="请输入商品名称" value="${name1}">
                        </div>
                    </div>
                    <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
                        <div class="form-group form-inline">
                            <span>上架时间</span>

                            <input type="text" name="startDate" placeholder="开始时间"
                                   class="form-control" readonly value="${startDate}"
                                   id="startDate"/>
                            -
                            <input type="text" name="endDate" placeholder="结束时间"
                                   class="form-control" readonly value="${endDate}"
                                   id="endDate">
                            <script>
                                laydate.render({
                                    elem: '#startDate',
                                    /*format:'yyyy-MM-dd HH:mm:ss'*/
                                });
                                laydate.render({
                                    elem: '#endDate'
                                });
                            </script>
                        </div>
                    </div>
                    <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                        <button type="submit" class="btn btn-primary" id="search"><span
                                class="glyphicon glyphicon-search"></span></button>
                    </div>
                    </form>
                </div>
                <div style="height: 400px;overflow: scroll;">
                    <table id="tb_list" class="table table-striped table-hover table-bordered">
                        <tr>
                            <td>序号</td>
                            <td>商品名称</td>
                            <td>价格</td>
                            <td>上架时间</td>
                            <td>类型</td>
                            <td>操作</td>
                        </tr>
                        <c:forEach items="${pageResult.data}" var="goods" varStatus="i">
                            <tr>
                                <td>${i.count}</td>
                                <td>${goods.name}</td>
                                <td>${goods.price}</td>
                                <td>${goods.pubdate}</td>
                                <td>${goods.typeid}</td>
                                <td>
                                    <button onclick="deleteGood(${goods.id})" class="btn btn-danger btn-xs">删除</button>
                                    <button onclick="toUpdate(${goods.id})" class="btn btn-success btn-xs">修改</button>
                                    <a tabindex="0" id="example${goods.id}" class="btn btn-primary btn-xs"
                                       role="button" data-toggle="popover"
                                       data-trigger="focus"
                                       data-placement="right"
                                       data-content="${goods.intro}">描述</a>
                                    <script type="text/javascript">
                                        $(function () {
                                            $("#example${goods.id}").popover();
                                        })
                                    </script>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="6"  style="text-align: center">
                                ${pageResult.pagination}
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    //删除商品
    function deleteGood(id){
        let b = confirm("确定要删除商品吗?");
        if (b){
            location.href = "/xiaomi/goods?method=deleteGoods&id=" + id;
        }
    }

    //修改商品信息
    function toUpdate(id){
        location.href = "/xiaomi/goods?method=jumpUpdateGoods&id=" + id;
    }
</script>

