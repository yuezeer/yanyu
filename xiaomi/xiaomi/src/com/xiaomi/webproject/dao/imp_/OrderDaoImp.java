package com.xiaomi.webproject.dao.imp_;

import com.xiaomi.webproject.bean.*;
import com.xiaomi.webproject.dao.OrderDao;
import com.xiaomi.webproject.util.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class OrderDaoImp implements OrderDao {
    private QueryRunner qr = new QueryRunner(JDBCUtilsByDruid.getDataSource());
    @Override
    public List showAllOrder() {
        String sql1 = "select * from tb_order";
        String sql2 = "select * from tb_user where id=?";
        List<Order> list = null;
        try {
            list = qr.query(sql1, new BeanListHandler<>(Order.class));
            for (Order order : list) {
                String uid = order.getUid();
                Admin admin = qr.query(sql2, new BeanHandler<>(Admin.class), uid);
                order.setAdmin(admin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean senderOrder(String id) {
        String sql = "update tb_order set status=3 where id=?";
        int update = 0;
        try {
            update = qr.update(sql, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update > 0;
    }

    @Override
    public List<CartItem> orderView(String cids) {
        String[] split = cids.split(",");
        String sql1 = "select * from tb_cart where cid=?";
        List<CartItem> cartItemsList = new ArrayList<>();
        for (String cid : split) {
            try {
                CartItem cartItems = qr.query(sql1, new BeanHandler<>(CartItem.class), cid);
                String pid = cartItems.getPid();
                String sql2 = "select * from tb_goods where id=?";
                Goods goods = qr.query(sql2, new BeanHandler<>(Goods.class), pid);
                cartItems.setGoods(goods);
                cartItemsList.add(cartItems);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return cartItemsList;
    }
    //添加订单
    @Override
    public boolean addOrder(Order order) {
        String sql = "insert into tb_order values(?,?,?,?,?,?,?)";
        try {
            int update = qr.update(sql, order.getId(), order.getUid(), order.getMoney()
                    , order.getStatus(), order.getTime(), order.getAid(), order.getCids());
            return update > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order orderDetail(Order order) {
        //先查询地址信息
        String aid = order.getAid();

        String sql = "select * from tb_address where id=?";
        try {
            Address address = qr.query(sql, new BeanHandler<>(Address.class), aid);
            order.setAddress(address);

            //查询订单中的购物条目信息
            String cids = order.getCids();

            String[] split = cids.split(",");

            List<CartItem> cartItems = new ArrayList<>();
            for (String cid : split) {
                sql = "select * from tb_cart where cid=?";
                CartItem cartItem = qr.query(sql, new BeanHandler<>(CartItem.class), cid);
                //查询每个购物条目中的商品
                String pid = cartItem.getPid();

                sql = "select * from tb_goods where id=?";
                Goods goods = qr.query(sql, new BeanHandler<>(Goods.class), pid);
                cartItem.setGoods(goods);
                cartItems.add(cartItem);
            }
            order.setCartItems(cartItems);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }
}
