package com.xiaomi.webproject.dao;

import com.xiaomi.webproject.bean.CartItem;
import com.xiaomi.webproject.bean.Order;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public interface OrderDao {
    List showAllOrder();
    //订单发货
    boolean senderOrder(String id);
    //根据商品主键查询商品信息
    List<CartItem> orderView(String cids);
    //添加订单
    boolean addOrder(Order order);
    //详细查询订单的信息
    Order orderDetail(Order order);
}
