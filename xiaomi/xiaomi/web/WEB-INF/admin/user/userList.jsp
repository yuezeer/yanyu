<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>小米网后台主页-会员信息页面</title>
    <link href="/xiaomi/css/bootstrap.min.css" rel="stylesheet">
    <script src="/xiaomi/js/jquery.min.js"></script>
    <script src="/xiaomi/js/bootstrap.min.js"></script>
</head>
<body>

<div class="row" style="width: 100%;">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">会员列表</div>
            <div class="panel-body">
                <!-- 条件查询 -->
                <form action="/xiaomi/admin?method=likeQuery" method="post">
                <div class="row">
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                        <div class="form-group form-inline">
                            <span>用户姓名</span>
                            <input type="text" name="username" class="form-control">
                        </div>
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <div class="form-group form-inline">
                            <span>性别</span>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <label class="radio-inline">
                                <input type="radio" name="gender" value="男"> 男&nbsp;&nbsp;&nbsp;&nbsp;
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" value="女"> 女
                            </label>
                        </div>
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <button  class="btn btn-primary" id="search"><span
                                class="glyphicon glyphicon-search"></span></button>
<%--                       <button class="btn btn-primary" id="search">提交</button>--%>
                    </div>
                </div>
               </form>
                <!-- 列表显示 -->
                <table id="tb_list" class="table table-striped table-hover table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>邮箱</th>
                        <th>类别</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${userDetails}" varStatus="i"
                               var="adminList">
                        <tr>
                            <td>${i.count}</td>
                            <td>${adminList.username}</td>
                            <td>${adminList.gender}</td>
                            <td>${adminList.email}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${adminList.role eq 1}">
                                        <span style="color: red">管理员</span>
                                    </c:when>
                                    <c:otherwise>
                                        会员
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <button class="btn btn-danger btn-sm" onclick="deleteAdmin(${adminList.id})" >删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="6">

                        </td>
                    </tr>
                </table>
<%--                        分页部分--%>
                <nav aria-label="Page navigation" style="margin: -27px auto;text-align: center">
                    <ul class="pagination">
                        <c:choose>
                        <c:when test="${pageNum != 1}">
                            <li>
                                <a href="/xiaomi/admin?method=list&page=${pageNum-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="javaScript:void(0)" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach begin="1" end="${pages}" var="page">
                            <c:choose>
                                <c:when test="${pageNum == page}">
                                    <li><a href="/xiaomi/admin?method=list&page=${page}"
                                           style="background-color: #2aabd2">${page}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/xiaomi/admin?method=list&page=${page}">${page}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${pageNum != pages}">
                                <li>
                                    <a href="/xiaomi/admin?method=list&page=${pageNum+1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="javaScript:void(0)" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function deleteAdmin(id){
    let b = confirm("是否删除用户?");
    if (b){
        location.href = "/xiaomi/admin?method=deleteAdmin&id="+id;
    }
}
</script>