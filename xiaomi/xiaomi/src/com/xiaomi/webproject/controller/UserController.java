package com.xiaomi.webproject.controller;

import cn.hutool.json.JSONUtil;
import com.xiaomi.webproject.bean.Admin;
import com.xiaomi.webproject.service.UserService;
import com.xiaomi.webproject.service.imp_.UserServiceImp;
import com.xiaomi.webproject.util.FormBeanUtil;
import com.xiaomi.webproject.util.MailThread;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈靖文
 * @version 1.0
 */
@WebServlet("/user")
public class UserController extends BaseServlet {
    private UserService userService = new UserServiceImp();

    //用户登录
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin user = FormBeanUtil.formToBean(Admin.class, req.getParameterMap());
        user = userService.login(user);
        String vcode = req.getParameter("vcode");
        Map<String, Object> map = new HashMap<>();
        //先校验验证码是否正确
        HttpSession session = req.getSession();
        String code = (String) session.getAttribute("code");
        if (code.equalsIgnoreCase(vcode)) {
            if (user != null) {
                //校验账号是否被激活
                boolean isActiveOk = userService.isActive(user);
                if (isActiveOk){
                    session.setAttribute("user", user);
                    map.put("flag", true);
                }
            } else {
                map.put("message", "用户名或者密码有误！");
                map.put("flag", false);
            }
        } else {
            map.put("message", "验证码错误");
            map.put("flag", false);
        }
        String message = JSONUtil.toJsonStr(map);
        resp.getWriter().write(message);

    }

    //用户注销
    public void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.invalidate();
        resp.sendRedirect("/xiaomi/login.jsp");
    }

    //验证用户是否重复注册
    public void repeatRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean isRepeat = userService.repeatRegister(username);
        Map<String, Object> map = new HashMap<>();//存储回调信息
        if (isRepeat) {
            map.put("message", "用户名已存在!");
            map.put("flag", true);
            String message = JSONUtil.toJsonStr(map);
            resp.getWriter().write(message);
        }
    }
    //用户注册
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin user = FormBeanUtil.formToBean(Admin.class, req.getParameterMap());
        boolean registerOk = userService.register(user);
        if (registerOk){
            //给用户发送邮件
            new Thread(new MailThread(user)).start();
            //跳转到注册成功界面
            resp.sendRedirect("/xiaomi/registerSuccess.jsp");
        }
    }
    //用户激活
    public void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取生成激活码的时间
        Long sendTime = Long.parseLong(req.getParameter("sendTime"));
        String email = req.getParameter("email");
        long currentTime = System.currentTimeMillis();
        if (currentTime - sendTime <= (60*3*1000)){
            //把用户flag设置成1
            boolean activeOk = userService.active(email);
            if (activeOk){
                req.setAttribute("activeMsg",true);
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }else {
                req.setAttribute("activeMsg",false);
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }

        }
    }

}
