package com.xiaomi.webproject.controller;

import cn.hutool.json.JSONUtil;
import com.xiaomi.webproject.bean.Address;
import com.xiaomi.webproject.bean.Admin;
import com.xiaomi.webproject.service.AddressService;
import com.xiaomi.webproject.service.imp_.AddressServiceImp;
import com.xiaomi.webproject.util.DateUtil;
import com.xiaomi.webproject.util.FormBeanUtil;
import com.xiaomi.webproject.util.UUIDUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
@WebServlet("/address")
public class AddressController extends BaseServlet{
    private AddressService addressService = new AddressServiceImp();
//添加收货地址
    public void addAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Address address = FormBeanUtil.formToBean(Address.class, req.getParameterMap());
        HttpSession session = req.getSession();
        //获取当前用户
        Admin user = (Admin) session.getAttribute("user");
        address.setUid(user.getId());
        address.setLevel("0");
        address.setCreatetime(DateUtil.dateToString());
//        address.setId(UUIDUtil.uuid());
        boolean addOk = addressService.addressService(address);
        if (addOk){
            //把地址返回给前端
//            String s = JSONUtil.toJsonStr(address);
            resp.getWriter().write(JSONUtil.toJsonStr(true));
        }
    }
    //异步查询当前用户的所有收货地址
    public void addressList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin user = (Admin) req.getSession().getAttribute("user");
        List<Address> addresses = addressService.addressList(user.getId());
        resp.getWriter().write(JSONUtil.toJsonStr(addresses));
    }
    //默认地址设置
    public void setDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        addressService.setDefault(id);
        resp.getWriter().write(JSONUtil.toJsonStr(true));
    }
    //删除地址
    public void deleteAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        boolean deleteOk = addressService.deleteAddress(id);
        if (deleteOk){
            resp.getWriter().write(JSONUtil.toJsonStr(true));
        }
    }
    //跳转的修改地址界面
    public void toUpdateAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
         Address address = addressService.queryById(id);
        HttpSession session = req.getSession();
        session.setAttribute("update","update");
        resp.getWriter().write(JSONUtil.toJsonStr(address));
    }
    //跳转的修改地址界面
    public void updateAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Address address = FormBeanUtil.formToBean(Address.class, req.getParameterMap());
        boolean updateOk = addressService.updateAddress(address);
        HttpSession session = req.getSession();
        if (updateOk){
            session.removeAttribute("update");
            resp.getWriter().write(JSONUtil.toJsonStr(true));
        }
    }

}
