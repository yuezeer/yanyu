package com.xiaomi.webproject.dao.imp_;

import com.xiaomi.webproject.bean.GoodType;
import com.xiaomi.webproject.dao.GoodsTypeDao;
import com.xiaomi.webproject.util.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class GoodsTypeDaoImp implements GoodsTypeDao {
    private QueryRunner qr = new QueryRunner(JDBCUtilsByDruid.getDataSource());

    @Override
    public List<GoodType> goodsType() {
        String sql = "select * from tb_goods_type";
        try {
            List<GoodType> list = qr.query(sql, new BeanListHandler<>(GoodType.class));
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addGoodsType(GoodType goodType) {
        String sql = "insert into tb_goods_type(name) values(?)";
        try {
            int update = qr.update(sql, goodType.getName());
            return update > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteGoodsType(String id) {
        String sql = "delete from tb_goods_type where id=?";
        int update = 0;
        try {
            update = qr.update(sql, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update > 0;
    }

    @Override
    public GoodType queryGoodsType(String id) {
        String sql = "select * from tb_goods_type where id=?";
        GoodType goodType = null;
        try {
            goodType = qr.query(sql, new BeanHandler<>(GoodType.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return goodType;
    }

    @Override
    public boolean updateGoodsType(GoodType goodType) {
        String sql = "update tb_goods_type set name=? where id=?";
        int update = 0;
        try {
            update = qr.update(sql, goodType.getName(), goodType.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update > 0;
    }
}
