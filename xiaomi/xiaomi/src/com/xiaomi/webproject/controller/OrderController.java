package com.xiaomi.webproject.controller;

import com.xiaomi.webproject.bean.Admin;
import com.xiaomi.webproject.bean.CartItem;
import com.xiaomi.webproject.bean.Order;
import com.xiaomi.webproject.service.OrderService;
import com.xiaomi.webproject.service.imp_.OrderServiceImp;
import com.xiaomi.webproject.util.DateUtil;
import com.xiaomi.webproject.util.FormBeanUtil;
import com.xiaomi.webproject.util.UUIDUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
@WebServlet("/order")
public class OrderController extends BaseServlet{
    private OrderService orderService = new OrderServiceImp();
    //后台显示订单信息
    public void showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List list = orderService.showAllOrder();
        req.setAttribute("orderList",list);
        req.getRequestDispatcher("/WEB-INF/admin/order/showAllOrder.jsp").forward(req,resp);
    }
    //订单发货
    public void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        boolean sendOk = orderService.senderOrder(id);
        if (sendOk){
            showOrder(req,resp);
        }
    }
    //显示当前用户订单信息
    public void toOrderView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车主键和商品总金额
        String cids = req.getParameter("cids");
        String totalMoney = req.getParameter("total");
        List<CartItem> cartItems = orderService.orderView(cids);
        req.setAttribute("totalMoney",totalMoney);
        req.setAttribute("cartItems",cartItems);
        req.setAttribute("cids",cids);
        req.getRequestDispatcher("/order.jsp").forward(req,resp);
    }
    //跳转到添加收获地址界面
    public void toAddAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("addressStyle","addressStyle");
        req.setAttribute("src","/xiaomi/address.jsp");
        req.getRequestDispatcher("/self_info.jsp")
                .forward(req,resp);
    }
    //提交订单
    public void addOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = FormBeanUtil.formToBean(Order.class, req.getParameterMap());
        HttpSession session = req.getSession();
        Admin user = (Admin) session.getAttribute("user");
        order.setId(UUIDUtil.uuid());//生成订单id
        order.setUid(user.getId());
        order.setStatus("1");
        order.setTime(DateUtil.dateToString());//获取生成订单的时间
        //添加订单
        boolean addOk = orderService.addOrder(order);
        if(addOk){
            //查询订单的详细信息
            order = orderService.orderDetail(order);
            req.setAttribute("order",order);
            req.getRequestDispatcher("/orderDetail.jsp")
                    .forward(req,resp);
        }

    }
}
