package com.xiaomi.webproject.controller;

import com.xiaomi.webproject.bean.GoodType;
import com.xiaomi.webproject.service.GoodsTypeService;
import com.xiaomi.webproject.service.imp_.GoodsTypeServiceImp;
import com.xiaomi.webproject.util.FormBeanUtil;

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
@WebServlet("/goodsType")
public class GoodsTypeController extends BaseServlet{
    private GoodsTypeService goodsTypeService = new GoodsTypeServiceImp();
    //显示商品种类
    public void showGoodsType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List list = goodsTypeService.goodsType();
        req.setAttribute("goodsTypeList",list);
        req.getRequestDispatcher("/WEB-INF/admin/good/showGoodsType.jsp").forward(req,resp);
    }
    //跳转添加商品种类界面
    public void jumpAddGoodsType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/good/addGoodsType.jsp").forward(req,resp);
    }
    //添加商品种类
    public void addGoodsType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("typename");
        GoodType goodType = new GoodType();
        goodType.setName(name);
        boolean addOk = goodsTypeService.addGoodsType(goodType);
        if (addOk){
            showGoodsType(req,resp);
        }
    }
    //删除商品种类
    public void deleteGoodsType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        boolean deleteOk = goodsTypeService.deleteGoodsType(id);
        if (deleteOk){
            showGoodsType(req,resp);
        }
    }

    //跳转修改商品种类界面
    public void jumpUpdateGoodsType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        GoodType goodType = goodsTypeService.queryGoodsType(id);
        req.setAttribute("goodType",goodType);
        req.getRequestDispatcher("/WEB-INF/admin/good/updateGoodsType.jsp").forward(req,resp);
    }
    //修改商品种类
    public void updateGoodsType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodType goodType = FormBeanUtil.formToBean(GoodType.class, req.getParameterMap());
        boolean deleteOk = goodsTypeService.updateGoodsType(goodType);
        if (deleteOk){
            showGoodsType(req,resp);
        }
    }
}
