package com.xiaomi.webproject.dao.imp_;

import com.xiaomi.webproject.bean.CartItem;
import com.xiaomi.webproject.bean.Goods;
import com.xiaomi.webproject.dao.CartDao;
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
public class CartDaoImp implements CartDao {
    private QueryRunner qr = new QueryRunner(JDBCUtilsByDruid.getDataSource());
    @Override
    public List<CartItem> CartItems(String uid) {
        //查询当前用户购物信息
        String sql1 = "select * from tb_cart where id=?";
        try {
            List<CartItem> cartItems = qr.query(sql1, new BeanListHandler<>(CartItem.class), uid);
            for (CartItem cartItem : cartItems) {
                String pid = cartItem.getPid();
                String sql2 = "select * from tb_goods where id=?";
                Goods goods = qr.query(sql2, new BeanHandler<>(Goods.class), pid);
                cartItem.setGoods(goods);
            }
            return cartItems;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCart(CartItem cartItem) {
        String sql = "update tb_cart set num=?,money=? where id=? and pid=?";
        try {
            qr.update(sql,cartItem.getNum(),cartItem.getMoney(),
                    cartItem.getId(),cartItem.getPid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //添加购物条目
    @Override
    public void addCartItem(CartItem cartItem) {
        String sql = "insert into tb_cart(id,pid,num,money) values(?,?,?,?)";
        try {
            qr.update(sql,cartItem.getId(),cartItem.getPid(),cartItem.getNum(),cartItem.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteCartItem(String cid) {
        String sql = "delete from tb_cart where cid=?";
        int update = 0;
        try {
            update = qr.update(sql, cid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update > 0;
    }

    @Override
    public boolean clearCart(String uid) {
        String sql = "delete from tb_cart where id=?";
        int update = 0;
        try {
            update = qr.update(sql, uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  update > 0;
    }
}
