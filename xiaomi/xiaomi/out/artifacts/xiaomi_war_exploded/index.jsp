<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8>
<link rel="stylesheet" href="/xiaomi/css/bootstrap.css">
<script src="/xiaomi/js/jquery.min.js"></script>
<script src="/xiaomi/js/bootstrap.js"></script>
<title>小米商城首页</title>
    <style>
       item img{
            width: 1226px;
            height: 460px;
        }
    </style>
</head>
<body>
<%--引入顶部页面--%>
<%@ include file="header.jsp"%>
<div id="myCarousel" class="carousel slide" data-ride="carousel"
     style="width: 1226px;height: 460px;margin-left: 145px">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <c:forEach items="${banners}" var="banner" varStatus="i">
            <c:if test="${i.index == 0}">
                <li data-target="#myCarousel" class="active"
                    data-slide-to="${i.index}"></li>
            </c:if>

            <c:if test="${i.index != 0}">
                <li data-target="#myCarousel" data-slide-to="${i.index}"></li>
            </c:if>
        </c:forEach>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <c:forEach items="${banners}" var="banner" varStatus="i">
            <c:choose>
                <c:when test="${i.index == 0}">
                    <div class="item active">
                        <img src="${banner.url}" >
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="item">
                        <img src="${banner.url}" >
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<br>
<div id="forth" style="width: 1226px;height: 460px;">
   		<span>
        	<a href=""><img src="image/hjh_01.gif" /></a>
            <a href=""><img src="image/hjh_02.gif" /></a>
            <a href=""><img src="image/hjh_03.gif" /></a>
            <a href=""><img src="image/hjh_04.gif" /></a>
            <a href=""><img src="image/hjh_05.gif" /></a>
            <a href=""><img src="image/hjh_06.gif" /></a>
        </span>
        <a href="" style="margin-left: 14px" ><img src="image/hongmi4x.png" width="316" height="170" /></a>
        <a href="" style="margin-left: 13px" ><img src="image/hongmin4.png" width="316" height="170" /></a>
    	<a href="" style="margin-left: 13px" ><img src="image/pinghengche.jpg" width="316" height="170" /></a>
</div>
<div style="width: 100%;height:100%;background: #F5F5F5;margin-top: -250px">
<%--    <img src="/image/into.webp"--%>
<%--         style="width: 1226px;margin: 20px 143px" />--%>
    <c:forEach items="${goodsTypeList}" var="goodType">
        <c:if test="${goodType.goods.size() > 0}">
            <div class="row">
                <h2>${goodType.name}</h2>
                <ul>
                    <c:forEach items="${goodType.goods}" var="good">
                        <li>
                            <a href="/xiaomi/goods?method=toDetail&id=${good.id}">
                                <div style="text-align: center">
                                    <img src="${good.picture}"
                                         style="width:160px;height:160px;margin-top: 24px" />
                                    <h5 style="color: #333">${good.name}</h5>
                                    <h6 style="color: #CCB7B0">${good.intro}</h6>
                                    <p><span style="color: #FF7D25">${good.price}元起</span></p>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    </c:forEach>

</div>
<!-- 底部 -->
<%@ include file="footer.jsp"%>
</body>
</html>
<style>
    .row{
        width: 1226px;
        margin: 20px 143px;
    }

    ul{
        margin: 0;
        padding: 0;
        list-style: none;
    }
    ul li{
        padding: 0;
        float: left;
        width: 233px;
        height: 300px;
        margin: 10px 6px;
        background: white;
        transition: all .2s linear;
    }

    ul li:hover{
        transform: translateY(-5px);
        box-shadow: 10px 10px 5px rgba(0,0,0,.1);;
    }

    li a{
        text-decoration: none;
        color: #0f0f0f;
        background-color: rgba(0,0,0,0);
    }

    li a:hover{
        text-decoration: none;
        color: #0f0f0f;
    }
</style>