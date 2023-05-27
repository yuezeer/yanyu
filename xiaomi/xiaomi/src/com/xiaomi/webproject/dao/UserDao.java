package com.xiaomi.webproject.dao;

import com.xiaomi.webproject.bean.Admin;

/**
 * @author 陈靖文
 * @version 1.0
 */
public interface UserDao {
    //用户登录
    Admin login(Admin admin);
    //验证用户是否重复注册
    boolean repeatRegister(String username);
    //注册用户
    boolean register(Admin user);
    //激活用户
    boolean active(String email);
    //验证是否成功激活用户
    boolean isActive(Admin user);
}
