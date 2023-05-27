package com.xiaomi.webproject.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 陈靖文
 * @version 1.0
 */
@WebServlet("/code")
public class CodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        //CircleCaptcha captcha = new CircleCaptcha(200, 100, 4, 20);
        String code = captcha.getCode();
        HttpSession session = req.getSession();
        session.setAttribute("code",code);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write(resp.getOutputStream());
        //验证图形验证码的有效性，返回boolean值

    }
}
