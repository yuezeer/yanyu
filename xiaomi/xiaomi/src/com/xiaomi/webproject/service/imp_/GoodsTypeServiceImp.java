package com.xiaomi.webproject.service.imp_;

import com.xiaomi.webproject.bean.GoodType;
import com.xiaomi.webproject.dao.GoodsTypeDao;
import com.xiaomi.webproject.dao.imp_.GoodsTypeDaoImp;
import com.xiaomi.webproject.service.GoodsTypeService;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class GoodsTypeServiceImp implements GoodsTypeService {
    private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImp();
    @Override
    public List<GoodType> goodsType() {
        return goodsTypeDao.goodsType();
    }

    @Override
    public boolean addGoodsType(GoodType goodType) {
        return goodsTypeDao.addGoodsType(goodType);
    }

    @Override
    public boolean deleteGoodsType(String id) {
        return goodsTypeDao.deleteGoodsType(id);
    }

    @Override
    public GoodType queryGoodsType(String id) {
        return goodsTypeDao.queryGoodsType(id);
    }

    @Override
    public boolean updateGoodsType(GoodType goodType) {
        return goodsTypeDao.updateGoodsType(goodType);
    }
}
