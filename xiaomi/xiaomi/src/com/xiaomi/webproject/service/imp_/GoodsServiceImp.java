package com.xiaomi.webproject.service.imp_;

import com.xiaomi.webproject.bean.Goods;
import com.xiaomi.webproject.dao.GoodsDao;
import com.xiaomi.webproject.dao.imp_.GoodsDaoImp;
import com.xiaomi.webproject.service.GoodsService;
import com.xiaomi.webproject.util.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class GoodsServiceImp implements GoodsService {
private GoodsDao goodsDao = new GoodsDaoImp();
    //显示物品信息
    @Override
    public PageResult<Goods> showGoods(int page, int pageSize, Map<String,Object> params) {
        return goodsDao.showGoods(page,pageSize,params);
    }

    @Override
    public boolean deleteGoods(String id) {
        return goodsDao.deleteGoods(id);
    }

    @Override
    public boolean addGoods(Goods goods) {
        return goodsDao.addGoods(goods);
    }

    @Override
    public Goods queryGoodsById(String id) {
        return goodsDao.queryGoodsById(id);
    }

    @Override
    public boolean updateGoods(Goods goods) {
        return goodsDao.updateGoods(goods);
    }

    @Override
    public List<Goods> findGoodsByTypeId(String typeId) {
        return goodsDao.findGoodsByTypeId(typeId);
    }

    @Override
    public Goods findGoodsById(String id) {
        return goodsDao.findGoodsById(id);
    }
}
