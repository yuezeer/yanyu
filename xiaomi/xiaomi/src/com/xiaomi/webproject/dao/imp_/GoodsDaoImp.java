package com.xiaomi.webproject.dao.imp_;

import com.xiaomi.webproject.bean.Goods;
import com.xiaomi.webproject.dao.GoodsDao;
import com.xiaomi.webproject.util.JDBCUtilsByDruid;
import com.xiaomi.webproject.util.PageResult;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class GoodsDaoImp implements GoodsDao {
    private QueryRunner qr = new QueryRunner(JDBCUtilsByDruid.getDataSource());
    //显示物品信息
    @Override
    public PageResult<Goods> showGoods(int page, int pageSize, Map<String,Object> params) {

        int start = (page - 1) * pageSize;//计算开始页
        StringBuffer sql1 = new StringBuffer("select t1.id,t1.name,t1.pubdate,t1.picture," +
                "t1.price,t1.star,t1.intro,t2.name" +
                " as typeid from tb_goods t1 left join " +
                "tb_goods_type t2 on t1.typeid=t2.id where 1=1");


        StringBuffer sql2 = new StringBuffer("select count(*) from tb_goods where 1=1");

        if(params.size() > 0){
            if(params.get("name1") != null){
                sql1.append(" and t1.name like ?");
                sql2.append(" and name like ?");
            }
            if(params.get("startDate") != null){
                sql1.append(" and pubdate >= ?");
                sql2.append(" and pubdate >= ?");
            }
            if(params.get("endDate") != null){
                sql1.append(" and pubdate <= ?");
                sql2.append(" and pubdate <= ?");
            }
        }

        sql1.append(" limit " + start + "," + pageSize);
        try {

            PageResult<Goods> pageResult = new PageResult(page,pageSize,Goods.class,sql1.toString(),params);

            Object[] objects = params.values().toArray();
            Number number = qr.query(sql2.toString(),new ScalarHandler<>(),objects);
            pageResult.setTotal(number.intValue());
            return pageResult;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteGoods(String id) {
        String sql = "delete from tb_goods where id=?";
        try {
            int update = qr.update(sql, id);
            return update > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
//添加商品信息
    @Override
    public boolean addGoods(Goods goods) {
        String sql = "insert into tb_goods(name,pubdate,picture,price" +
                ",star,intro,typeid) values(?,?,?,?,?,?,?)";
        try {
            int update = qr.update(sql, goods.getName(), goods.getPubdate(), goods.getPicture(),
                    goods.getPrice(), goods.getStar(), goods.getIntro(), goods.getTypeid());
            return update > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Goods queryGoodsById(String id) {
        String sql = "select * from tb_goods where id=?";
        try {
            Goods goods = qr.query(sql, new BeanHandler<>(Goods.class), id);
            return goods;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateGoods(Goods goods) {
        String sql = "update tb_goods set name=?,pubdate=?,picture=?" +
                ",price=?,star=?,intro=?,typeid=? where id=?";
        try {
            int update = qr.update(sql, goods.getName(), goods.getPubdate(), goods.getPicture(), goods.getPrice(),
                    goods.getStar(), goods.getIntro(), goods.getTypeid(),goods.getId());
            return update > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Goods> findGoodsByTypeId(String typeId) {
        String sql = "select * from tb_goods where typeid=?";
        List<Goods> goods = null;
        try {
            goods = qr.query(sql, new BeanListHandler<>(Goods.class), typeId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return goods;
    }

    @Override
    public Goods findGoodsById(String id) {
        String sql = "select t1.id,t1.name,t1.pubdate,t1.picture,t1.price,t1.star,t1.intro,t2.name as typeid from tb_goods " +
                "t1 left join tb_goods_type t2 on t1.typeid=t2.id where t1.id=?";
        Goods goods = null;
        try {
            goods = qr.query(sql, new BeanHandler<>(Goods.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return goods;
    }
}
