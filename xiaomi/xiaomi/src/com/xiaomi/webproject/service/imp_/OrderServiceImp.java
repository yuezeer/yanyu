package com.xiaomi.webproject.service.imp_;

import com.xiaomi.webproject.bean.CartItem;
import com.xiaomi.webproject.bean.Order;
import com.xiaomi.webproject.dao.OrderDao;
import com.xiaomi.webproject.dao.imp_.OrderDaoImp;
import com.xiaomi.webproject.service.OrderService;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class OrderServiceImp implements OrderService {
private OrderDao orderDao = new OrderDaoImp();
    @Override
    public List showAllOrder() {
        return orderDao.showAllOrder();
    }

    @Override
    public boolean senderOrder(String id) {
        return orderDao.senderOrder(id);
    }

    @Override
    public List<CartItem> orderView(String cids) {
        return orderDao.orderView(cids);
    }

    @Override
    public boolean addOrder(Order order) {
        //添加订单
        return orderDao.addOrder(order);
    }
    //详细查询订单的信息
    @Override
    public Order orderDetail(Order order) {
        return orderDao.orderDetail(order);
    }
}
