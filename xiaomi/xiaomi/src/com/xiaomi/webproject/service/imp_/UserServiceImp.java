package com.xiaomi.webproject.service.imp_;

import com.xiaomi.webproject.bean.Admin;
import com.xiaomi.webproject.dao.UserDao;
import com.xiaomi.webproject.dao.imp_.UserDaoImp;
import com.xiaomi.webproject.service.UserService;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class UserServiceImp implements UserService {
    private UserDao userDao = new UserDaoImp();
    @Override
    public Admin login(Admin admin) {
        return userDao.login(admin);
    }

    @Override
    public boolean repeatRegister(String username) {
        return userDao.repeatRegister(username);
    }

    @Override
    public boolean register(Admin user) {
        return userDao.register(user);
    }

    @Override
    public boolean active(String email) {
        return userDao.active(email);
    }

    @Override
    public boolean isActive(Admin user) {
        return userDao.isActive(user);
    }
}
