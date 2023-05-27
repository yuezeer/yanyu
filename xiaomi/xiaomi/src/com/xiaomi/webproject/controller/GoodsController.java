package com.xiaomi.webproject.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.xiaomi.webproject.bean.Goods;
import com.xiaomi.webproject.service.GoodsService;
import com.xiaomi.webproject.service.GoodsTypeService;
import com.xiaomi.webproject.service.imp_.GoodsServiceImp;
import com.xiaomi.webproject.service.imp_.GoodsTypeServiceImp;
import com.xiaomi.webproject.util.FormBeanUtil;
import com.xiaomi.webproject.util.PageResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈靖文
 * @version 1.0
 */
@WebServlet("/goods")
@MultipartConfig
public class GoodsController extends BaseServlet{
    private GoodsService goodsService = new GoodsServiceImp();
    private GoodsTypeService goodsTypeService = new GoodsTypeServiceImp();
    //显示物品信息
    private int pageSize = 6;//每页显示的商品数量
    public void showGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //获取当前页
        int page = req.getParameter("page") == null ? 1
                : Integer.parseInt(req.getParameter("page"));
        //获取查询参数，封装到map中
        String name = req.getParameter("name1");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        Map<String,Object> params = new LinkedHashMap<>();
        if(StrUtil.isNotEmpty(name)){
            params.put("name1","%" + name + "%");
        }
        if(StrUtil.isNotEmpty(startDate)){
            params.put("startDate",startDate);
        }
        if(StrUtil.isNotEmpty(endDate)){
            params.put("endDate",endDate);
        }
        PageResult<Goods> pageResult = goodsService.showGoods(page, pageSize, params);
        //设置点击页码的请求地址
        pageResult.setUrl("/xiaomi/goods?method=showGoods");
        req.setAttribute("pageResult",pageResult);

        //条件回显
        req.setAttribute("name1",name);
        req.setAttribute("startDate",startDate);
        req.setAttribute("endDate",endDate);
        //转发到商品列表页面
        req.getRequestDispatcher("/WEB-INF/admin/good/showGoods.jsp")
                .forward(req,resp);
    }
    //跳转添加商品信息界面
    public void jumpAddGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher("/WEB-INF/admin/good/addGoods.jsp").forward(req,resp);
    }
//添加商品信息
    public void addGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Goods goods = FormBeanUtil.formToBean(Goods.class, req.getParameterMap());
        boolean addOk = goodsService.addGoods(goods);
        if (addOk){
            showGoods(req,resp);
        }
    }
    //删除商品
    public void deleteGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        boolean b = goodsService.deleteGoods(id);
        if (b){
            showGoods(req,resp);
        }
    }
    //跳转修改商品信息界面
    public void jumpUpdateGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Goods goods = goodsService.queryGoodsById(id);
        req.setAttribute("goods",goods);
        req.getRequestDispatcher("/WEB-INF/admin/good/updateGoods.jsp").forward(req,resp);
    }
    //修改商品信息
    public void updateGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Goods goods = FormBeanUtil.formToBean(Goods.class, req.getParameterMap());
        boolean updateOk = goodsService.updateGoods(goods);
        if (updateOk){
            showGoods(req,resp);
        }
    }

    //异步查询商品种类
    public void queryGoodsType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List list = goodsTypeService.goodsType();
        String jsonStr = JSONUtil.toJsonStr(list);
        resp.getWriter().write(jsonStr);
    }
    //异步上传图片
    public void uploadPicture(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        String realPath = req.getServletContext().getRealPath("/upload");
        File file = new File(realPath);

        if(!file.exists()){
            file.mkdirs();
        }
        //防止文件重名
        String fileName = part.getSubmittedFileName();
        fileName = System.currentTimeMillis()  + fileName;

        //..../upload/fileName
        part.write(realPath + File.separator + fileName);

        Map<String,String> map = new HashMap<>();
        map.put("msg","上传成功");
        String path = "/upload" + File.separator + fileName;
        map.put("url",path);
        String s = JSONUtil.toJsonStr(map);
        resp.getWriter().write(s);
    }
    //根据id查询商品信息
    public void toDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Goods goods = goodsService.findGoodsById(id);
        req.setAttribute("goods",goods);
        req.getRequestDispatcher("/goodsDetail.jsp").forward(req,resp);
    }

}
