package com.xiaomi.webproject.controller;

import com.xiaomi.webproject.bean.Admin;
import com.xiaomi.webproject.bean.CartItem;
import com.xiaomi.webproject.bean.Goods;
import com.xiaomi.webproject.service.CartService;
import com.xiaomi.webproject.service.imp_.CartServiceImp;

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
@WebServlet("/cart")
public class CartController extends BaseServlet{
    private CartService cartService = new CartServiceImp();
    //购物车信息
    public void cartList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前用户信息
        HttpSession session = req.getSession();
        Admin user = (Admin) session.getAttribute("user");
        //获取商品主键和单价
        String pid = req.getParameter("pid");
        String price = req.getParameter("price");
        //获取进入购物车的方式
        String type = req.getParameter("type");
        //获取当前登录用户的购物信息
        List<CartItem> cartItems = cartService.CartItems(user.getId());
        //判断购物购物车是否有商品
        boolean hasGoods = false;
        CartItem newCartItem = null;//存放修改后的购物条目
        if (type.equals("header")) {
            if (cartItems.size() == 0){
                //购物车是空的，跳转到空空如也界面
                resp.sendRedirect("/xiaomi/emptyCar.jsp");
                return;
            }else {
                req.setAttribute("cartItems",cartItems);
                req.getRequestDispatcher("/cart.jsp").forward(req,resp);
                return;
            }
        }else {
            if (cartItems != null){
                //判断当前用户购物车是否有商品
                for (CartItem cartItem : cartItems) {
                    String uid = cartItem.getId();
                    String pid1 = cartItem.getPid();
                    if (uid.equals(user.getId()) && pid1.equals(pid)) {
                        hasGoods = true;
                        newCartItem = cartItem;
                        break;
                    }
                }
            }
        }
        if (hasGoods){
            //修改数量和小结
            newCartItem.setNum(newCartItem.getNum() + 1);
            Goods goods = newCartItem.getGoods();
            newCartItem.setMoney(Float.parseFloat(goods.getPrice()) * newCartItem.getNum());
            cartService.updateCart(newCartItem);
        }else {
            //添加一条新的购物条目
            CartItem cartItem = new CartItem();
            cartItem.setId(user.getId());
            cartItem.setPid(pid);
            cartItem.setNum(1);
            cartItem.setMoney(Float.parseFloat(price));
            cartService.addCartItem(cartItem);
        }
        //再次查询当前登录用户购物车数据
        cartItems = cartService.CartItems(user.getId());
        req.setAttribute("cartItems",cartItems);
        req.getRequestDispatcher("/cart.jsp").forward(req,resp);
    }
    //删除购物车条目
    public void deleteCartItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");
        boolean deleteOk = cartService.deleteCartItem(cid);
        if (deleteOk){
            HttpSession session = req.getSession();
            Admin user = (Admin) session.getAttribute("user");
            List<CartItem> cartItems = cartService.CartItems(user.getId());
            req.setAttribute("cartItems",cartItems);
            req.getRequestDispatcher("/cart.jsp").forward(req,resp);
        }
    }
    //清空购物车条目
    public void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin user = (Admin) session.getAttribute("user");
        boolean clearCartOk = cartService.clearCart(user.getId());
        if (clearCartOk){
           resp.sendRedirect("/xiaomi/emptyCar.jsp");
        }
    }
}
