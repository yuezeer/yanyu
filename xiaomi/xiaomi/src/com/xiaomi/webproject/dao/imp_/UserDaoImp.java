package com.xiaomi.webproject.dao.imp_;

import cn.hutool.crypto.SecureUtil;
import com.xiaomi.webproject.bean.Admin;
import com.xiaomi.webproject.dao.UserDao;
import com.xiaomi.webproject.util.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class UserDaoImp implements UserDao {
    private QueryRunner qr = new QueryRunner(JDBCUtilsByDruid.getDataSource());
    //用户登录
    @Override
    public Admin login(Admin admin) {
        String sql = "select * from tb_user where username=? and password=?";
        String password = SecureUtil.md5(admin.getPassword());
        Admin user = null;
        try {
            user = qr.query(sql, new BeanHandler<>(Admin.class), admin.getUsername(), password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
    //验证用户是否已被注册
    @Override
    public boolean repeatRegister(String username) {
        String sql = "select count(*) from tb_user where username=?";
        Number number = null;
        try {
            number = qr.query(sql, new ScalarHandler<>(), username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return number.intValue() > 0;
    }
    //用户注册
    @Override
    public boolean register(Admin user) {
        String sql = "insert into tb_user(username,password,email,gender,flag,role,code) values(?,?,?,?,?,?,?)";
        int update = 0;
        String password = SecureUtil.md5(user.getPassword());
        try {
            update = qr.update(sql, user.getUsername(), password, user.getEmail(), user.getGender()
                    ,0, 0, user.getCode());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update > 0;
    }
    //激活用户
    @Override
    public boolean active(String email) {
        String sql = "update tb_user set flag=1 where email=?";
        int update = 0;
        try {
            update = qr.update(sql, email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update > 0;
    }
    //验证是否成功激活用户
    @Override
    public boolean isActive(Admin user) {
        String sql = "select count(*) from tb_user where flag=1 and username=? and password=?";
        Number number = null;
        try {
            number = qr.query(sql, new ScalarHandler<>(),user.getUsername(),user.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return number.intValue() > 0;
    }
}
