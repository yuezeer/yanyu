package com.xiaomi.webproject.dao;

import com.xiaomi.webproject.bean.Goods;
import com.xiaomi.webproject.util.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @author 陈靖文
 * @version 1.0
 */
public interface GoodsDao {
    //显示物品信息
    PageResult<Goods> showGoods(int page, int pageSize, Map<String,Object> params);

    //删除商品
    boolean deleteGoods(String id);
    //添加商品信息
    boolean addGoods(Goods goods);
    //根据id查询商品信息
    Goods queryGoodsById(String id);
    //修改商品信息
    boolean updateGoods(Goods goods);
    //根据商品种类查询商品
    List<Goods> findGoodsByTypeId(String typeId);
    //根据商品id查询商品
    Goods findGoodsById(String id);
}
