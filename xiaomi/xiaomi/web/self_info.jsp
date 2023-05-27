<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="/xiaomi/css/login2.css">
    <link rel="stylesheet" href="/xiaomi/css/bootstrap.min.css"/>
    <script src="/xiaomi/js/jquery.min.js"></script>
    <script src="/xiaomi/js/bootstrap.min.js"></script>
    <title>个人中心-收货地址页面</title>
    <style>
        #big_left a {
            display: inline-block;
            padding-top: 18px;
        }
    </style>
</head>
<script>
    $(function () {
        $('#big_left a').each(function () {
            $(this).click(function () {
                $(this).css({'color': 'red', 'fontWeight': 'bold'});
                $(this).siblings().each(function (){
                    $(this).css({'color': '#337ab7', 'fontWeight': 'normal'});
                })
            })
        });
    });
</script>
<body>

<%@ include file="header.jsp" %>
<!--网站中间内容开始-->
<div id="dingdanxiangqing_body" style="height: 900px">
    <div id="dingdanxiangqing_body_big">
        <div id="big_left" style="width:210px;height: 600px">
            <p style="font-size:18px;margin-top: 15px">个人中心</p>
            <c:if test="${not empty orderStyle}">
                <a id="big_left_a" style="color: red;font-weight: bold"
                   href="javascript:toOrder()">我的订单</a><br/>
            </c:if>
            <c:if test="${empty orderStyle}">
                <a id="big_left_a" href="javascript:toOrder()">我的订单</a><br/>
            </c:if>

            <c:if test="${not empty addressStyle}">
                <a id="big_left_a" style="color: red;font-weight: bold"
                   href="javascript:toAddress()">收货地址</a><br/>
            </c:if>
            <c:if test="${empty addressStyle}">
                <a id="big_left_a" href="javascript:toAddress()">收货地址</a><br/>
            </c:if>
            <a id="big_left_a" href="">意外保</a><br/>
            <a id="big_left_a" href="">团购订单</a><br/>
            <a id="big_left_a" href="">评价晒单</a><br/>
            <a id="big_left_a" href="self_info.html">我的个人中心</a><br/>
            <a id="big_left_a" href="">消息通知</a><br/>
            <a id="big_left_a" href="">优惠券</a><br/>
        </div>
        <div id="big_right" style="">
            <br>
            <div class="page_main">
                <iframe id="content" src="${src}" style="width: 100%;height: 750px"
                        frameborder="0" scrolling="no">

                </iframe>
            </div>

        </div>
    </div>
</div>

<!-- 底部 -->
<%--<%@ include file="footer.jsp"%>--%>
</body>
</html>
<script>

    //跳转到收货地址页面
    function toAddress() {
        location.href = "/xiaomi/order?method=toAddAddress";
    }

    //跳转到订单列表页面
    function toOrder() {
        $('#content').prop('src', '/order?method=orderList');
    }

</script>