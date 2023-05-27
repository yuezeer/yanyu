<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/xiaomi/css/bootstrap.min.css"/>
    <script src="/xiaomi/js/jquery.min.js"></script>
    <script src="/xiaomi/js/bootstrap.min.js"></script>
    <style>
        span {
            display: inline-block;
            max-width: 100%;
            margin-bottom: 5px;
            font-weight: 700;
        }
    </style>
</head>
<body>

<div style="margin:-20px 20px;">
    <br><br>
    <h3>收货地址</h3>
    <hr>
    <table
            class="table table-striped table-hover table-bordered">
        <tr>
            <th>地址编号</th>
            <th>收件人</th>
            <th>手机号</th>
            <th>所在地区</th>
            <th>收货地址</th>
            <th>操作</th>
        </tr>
        <tbody id="address">

        </tbody>
    </table>
</div>

<div class="container" style="width:100%;">
    <hr>
    <form id="addressForm" method="post">
        <input type="hidden" name="id" id="aid" value="${address.id}">
        <div class="form-group" style="position: relative">
            <label style="float:left">地址</label>
            <span>信息 </span>
            <select name="province" id="province"
                    style="width: 200px;position: absolute;"
                    class="form-control">
            </select>
            <select name="city" id="city" style="width: 200px;position: absolute;
      left: 225px" class="form-control">
            </select>
            <select name="region" id="region" style="width: 200px;position: absolute;
      left: 450px;top:25px" class="form-control">
            </select>
        </div>
        <br/>
        <div class="form-group" style="margin-top: 15px">
            <label for="detailAddress">详细地址</label>
            <textarea name="detail" id="detail" class="form-control"
                      placeholder="请输入详细地址信息，如道路、门牌号、小区、楼栋号、单元等信息"
                      rows="3">${address.detail}</textarea>
        </div>
        <div class="form-group">
            <label for="receive">收货人姓名</label>
            <input value="${address.receiver}" name="receiver" type="text" class="form-control"
                   id="receiver" placeholder="长度不超过25个字符">
        </div>
        <div class="form-group">
            <label for="phoneInput">手机号码</label>
            <input value="${address.phone}" type="number" name="phone" class="form-control"
                   id="phone" placeholder="请输入手机号码">
        </div>
        <c:if test="${not empty sessionScope.update}">
            <button type="button" onclick="updateAddress()" class="btn btn-primary">修改地址</button>
        </c:if>
        <c:if test="${empty sessionScope.update}">
            <button onclick="addAddress()" type="button" id="btn" class="btn btn-primary">添加地址</button>
        </c:if>
        <button onclick="history.go(-1)" class="btn btn-default">返回订单</button>
    </form>
    <%--  <button onclick="history.go(-1)"  class="btn btn-default">返回订单</button>--%>

</div>
</body>
</html>
<script src="/xiaomi/js/data.js"></script>
<link rel="stylesheet" href="/xiaomi/js/layui/css/layui.css">
<script src="/xiaomi/js/layui/layui.js"></script>
<script>
    refresh();

    //跳转到修改页面
    function toUpdateAddress(id){
        $.get('/xiaomi/address?method=toUpdateAddress',{
            id:id
        },function (data){
            //data:address
            let address = data;
            $('#aid').val(address.id)
            $('#province').val(address.province);
            $('#city').val(address.city);
            $('#region').val(address.region);
            $('#detail').val(address.detail);
            $('#receiver').val(address.receiver);
            $('#phone').val(address.phone);
        },'json');
    }


    //删除地址
    function deleteAddress(id) {
        layer.confirm('确认删除地址吗？', {
            btn: ['确定', '取消'] //可以无限个按钮
            , btn3: function (index, layero) {
                //按钮【按钮三】的回调
            }
        }, function (index, layero) {
            //发送删除请求
            $.get('/xiaomi/address?method=deleteAddress&id=' + id, function (data) {
                if (data) {
                    refresh();
                }
            }, 'json');
            layer.close(index);
        }, function (index) {
            layer.close(index);
            //按钮【按钮二】的回调
        });
    }


    function refresh() {
        //异步查询当前用户的所有地址信息
        $.post('/xiaomi/address?method=addressList',
            $('#addressForm').serialize(), function (data) {
                $('#address').html("");
                //data:List<Address>
                for (let address of data) {
                    if (address.level == 1) {
                        $('#address').append("<tr><td>" + address.id + "</td>" +
                            " <td>" + address.receiver + "</td>" +
                            " <td>" + address.phone + "</td>" +
                            " <td>" + address.province + address.city + address.region + "</td>" +
                            " <td>" + address.detail + "</td>" +
                            "<td><button onclick=\"deleteAddress('" + address.id + "')\" class='btn btn-danger btn-sm'>删除</button>" +
                            "<button onclick=\"toUpdateAddress('" + address.id + "')\" class='btn btn-sm btn-success'>修改</button>" +
                            "</tr>");
                    } else {
                        $('#address').append("<tr><td>" + address.id + "</td>" +
                            " <td>" + address.receiver + "</td>" +
                            " <td>" + address.phone + "</td>" +
                            " <td>" + address.province + address.city + address.region + "</td>" +
                            " <td>" + address.detail + "</td>" +
                            "<td><button onclick=\"deleteAddress('" + address.id + "')\" class='btn btn-danger btn-sm'>删除</button>" +
                            "<button onclick=\"toUpdateAddress('" + address.id + "')\" class='btn btn-sm btn-success'>修改</button>" +
                            "<button onclick=\"setDefault('" + address.id + "')\" class='btn btn-sm btn-default'>设为默认</button></td>" +
                            "</tr>");
                    }
                }

            }, 'json');
    }

    //设为默认地址
    function setDefault(id) {
        $.get('/xiaomi/address?method=setDefault&id=' + id, function (data) {
            if (data) {
                refresh();
            }
        }, 'json');
    }

    //添加收货地址
    function addAddress() {
        $.post('/xiaomi/address?method=addAddress',
            $('#addressForm').serialize(), function (data) {
                if (data) {
                    refresh();
                }
                //jquery没有重置表单的函数，只能用原生态js编写
                document.querySelector('#addressForm').reset();
            }, 'json');
    }
    //修改地址
    function updateAddress(){
        $.post('/xiaomi/address?method=updateAddress',$('#addressForm').serialize(),
            function (data) {
                if(data){
                    refresh();
                    //jquery没有重置表单的函数，只能用原生态js编写
                    location.reload(true);
                }
            },'json');
    }
</script>