package com.xiaomi.webproject.controller;

import com.xiaomi.webproject.bean.Banner;
import com.xiaomi.webproject.bean.GoodType;
import com.xiaomi.webproject.bean.Goods;
import com.xiaomi.webproject.service.BannerService;
import com.xiaomi.webproject.service.imp_.BannerServiceImp;
import com.xiaomi.webproject.service.GoodsService;
import com.xiaomi.webproject.service.GoodsTypeService;
import com.xiaomi.webproject.service.imp_.GoodsServiceImp;
import com.xiaomi.webproject.service.imp_.GoodsTypeServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
@WebServlet("/index")
public class IndexController extends BaseServlet{
    private BannerService bannerService = new BannerServiceImp();
    private GoodsTypeService goodsTypeService = new GoodsTypeServiceImp();
    private GoodsService goodsService = new GoodsServiceImp();
    //轮播图
    public void banners(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //轮播图数据
        List<Banner> banners = bannerService.banners();
        req.setAttribute("banners",banners);
        //查询所有商品数据
        //查询所有商品种类
        List<GoodType> goodTypes = goodsTypeService.goodsType();
        for (GoodType goodType : goodTypes) {
            String typeId = goodType.getId();
            List<Goods> goods = goodsService.findGoodsByTypeId(typeId);
            goodType.setGoods(goods);
        }
        req.setAttribute("goodsTypeList",goodTypes);
        //转发前台首页
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}
