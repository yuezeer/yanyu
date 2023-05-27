package com.xiaomi.webproject.dao;

import com.xiaomi.webproject.bean.CartItem;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public interface CartDao {
    //当前用户显示购物车条目
    List<CartItem> CartItems(String uid);
    //修改购物车条目
    void updateCart(CartItem cartItem);
    //添加购物车条目
    void addCartItem(CartItem cartItem);
    //删除购物车条目
    boolean deleteCartItem(String cid);
    //清空购物车
    boolean clearCart(String uid);
}
