package com.xiaomi.webproject.controller;

import com.xiaomi.webproject.bean.Admin;
import com.xiaomi.webproject.service.AdminService;
import com.xiaomi.webproject.service.imp_.AdminServiceImp;
import com.xiaomi.webproject.util.FormBeanUtil;

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
@WebServlet("/admin")
public class AdminController extends BaseServlet{
    private AdminService adminService = new AdminServiceImp();
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/login.jsp").forward(req,resp);
    }
    //管理员登录
    public void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin admin = FormBeanUtil.formToBean(Admin.class, req.getParameterMap());
        admin = adminService.adminLogin(admin);
        if (admin != null){
            HttpSession session = req.getSession();
            session.setAttribute("admin",admin);
            req.getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(req,resp);
        }else {

        }
    }
    //注销
    public void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.invalidate();
        req.getRequestDispatcher("/WEB-INF/admin/login.jsp").forward(req,resp);
    }
    //显示用户信息
    public void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      list(req,resp);
    }
//    //计算分页
//    public void listPage(HttpServletRequest req, HttpServletResponse resp,int count) throws ServletException, IOException {
//
//    }
    //查询用户信息分页
    private int pageSize = 5;//每页显示5条用户信息
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int count = adminService.count();
        //计算分页数量
        int pages = count % pageSize == 0 ?count/pageSize:(count/pageSize) + 1;
        //获取页码
        String page = req.getParameter("page");
        //获取第几页
        int pageNum = page==null ? 1 : Integer.parseInt(page);

        //查询学生
        List list = adminService.listUser(pageNum,pageSize);
        req.setAttribute("userDetails",list);
        req.setAttribute("pages",pages);
        req.setAttribute("pageNum",pageNum);
        req.getRequestDispatcher("/WEB-INF/admin/user/userList.jsp").forward(req,resp);
    }
    //搜索栏，模糊查询
    //查询用户数量
    public void likeQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if (username.equals("")){
            username = null;
        }
        String gender = req.getParameter("gender");
        int count = adminService.likeCount(username, gender);
        //计算分页数量
        int pages = count % pageSize == 0 ?count/pageSize:(count/pageSize) + 1;
        //获取页码
        String page = req.getParameter("page");
        //获取第几页
        int pageNum = page==null ? 1 : Integer.parseInt(page);
        List list = adminService.likeQuery(username, gender,pageNum,pageSize);
        req.setAttribute("userDetails",list);
        req.setAttribute("pages",pages);
        req.setAttribute("pageNum",pageNum);
        req.getRequestDispatcher("/WEB-INF/admin/user/userList.jsp").forward(req,resp);
    }
    //删除用户信息
    public void deleteAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        boolean deleteOK = adminService.deleteAdmin(id);
        if (deleteOK){
            list(req,resp);
        }
    }
}
