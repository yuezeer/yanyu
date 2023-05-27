package com.xiaomi.webproject.dao;

import com.xiaomi.webproject.bean.GoodType;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public interface GoodsTypeDao {
    //显示商品种类
    List<GoodType> goodsType();
    //添加商品种类
    boolean addGoodsType(GoodType goodType);
    //删除商品种类
    boolean deleteGoodsType(String id);
    //根据id查商品种类
    GoodType queryGoodsType(String id);
    //修改商品id
    boolean updateGoodsType(GoodType goodType);
}
