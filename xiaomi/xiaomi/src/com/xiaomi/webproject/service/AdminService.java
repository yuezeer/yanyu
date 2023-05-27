package com.xiaomi.webproject.service;

import com.xiaomi.webproject.bean.Admin;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public interface AdminService {
    //管理员登录
    Admin adminLogin(Admin admin);

    //显示用户信息
    List listUser(int page, int pageSize);

    //查询用户数量
    int count();
    //模糊查询
    List likeQuery(String username ,String gender ,int page, int pageSize);

    //模糊查询人数
    int likeCount(String username ,String gender);
    //删除用户信息
    boolean deleteAdmin(String id);
}
