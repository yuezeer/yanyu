package com.xiaomi.webproject.service.imp_;

import cn.hutool.crypto.SecureUtil;
import com.xiaomi.webproject.bean.Admin;
import com.xiaomi.webproject.dao.AdminDao;
import com.xiaomi.webproject.dao.imp_.AdminDaoImp;
import com.xiaomi.webproject.service.AdminService;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class AdminServiceImp implements AdminService {
    private AdminDao adminDao = new AdminDaoImp();
    //管理员登录
    @Override
    public Admin adminLogin(Admin admin) {
        admin.setPassword(SecureUtil.md5(admin.getPassword()));
        return adminDao.adminLogin(admin);
    }

    @Override
    public List listUser(int page, int pageSize) {
        List list = adminDao.listUser(page,pageSize);
        return list;
    }

    @Override
    public int count() {
        int count = adminDao.count();
        return count;
    }

    @Override
    public List likeQuery(String username, String gender ,int page, int pageSize) {
        List list = adminDao.likeQuery(username, gender,page,pageSize);
        return list;
    }

    @Override
    public int likeCount(String username, String gender) {
        int count = adminDao.likeCount(username, gender);
        return count;
    }

    @Override
    public boolean deleteAdmin(String id) {
        return adminDao.deleteAdmin(id);
    }
}
