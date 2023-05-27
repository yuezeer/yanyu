package com.xiaomi.webproject.dao.imp_;

import com.xiaomi.webproject.bean.Admin;
import com.xiaomi.webproject.dao.AdminDao;
import com.xiaomi.webproject.util.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class AdminDaoImp implements AdminDao {
    private QueryRunner queryRunner = new QueryRunner(JDBCUtilsByDruid.getDataSource());
    //管理员登录
    @Override
    public Admin adminLogin(Admin admin) {
        String sql = "select * from tb_user where username=? and password=?";
        try {
            admin = queryRunner.query(sql, new BeanHandler<>(Admin.class), admin.getUsername(), admin.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admin;
    }
//显示用户信息
    @Override
    public List listUser(int page, int pageSize) {
        int a = (page - 1) * pageSize;
       String sql = "select * from tb_user limit " + a + "," + pageSize;
        List<Admin> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<>(Admin.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
//查询用户数量
    @Override
    public int count() {
       String sql = "select count(*) from tb_user";
       Number number = null;
        try {
            number = queryRunner.query(sql,new ScalarHandler<>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return number.intValue();
    }
//模糊查询，搜索
    @Override
    public List likeQuery(String username, String gender ,int page, int pageSize) {
        int a = (page - 1) * pageSize;
        List<Admin> list = null;
        StringBuilder sb = new StringBuilder("select * from tb_user where 1 = 1 ");
        if (username != null){
            sb.append("and username like '%" + username + "%'");
        }
        if (gender != null){
            sb.append(" and gender = " + " \'" + gender + " \'");
        }
        String sql = sb.toString() + " order by id limit "+ a + "," + pageSize;
        try {
            list = queryRunner.query(sql, new BeanListHandler<>(Admin.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public int likeCount(String username, String gender) {
        //模糊查询人数
        Number number = null;
        StringBuilder sb = new StringBuilder("select count(*) from tb_user where 1 = 1 ");
        if (username != null){
            sb.append("and username like '%" + username + "%'");
        }
        if (gender != null){
            sb.append(" and gender = " + " \'" + gender + " \'");
        }
        try {
            number = queryRunner.query(sb.toString(),new ScalarHandler<>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return number.intValue();
    }

    @Override
    public boolean deleteAdmin(String id) {
        String sql = "delete from tb_user where id=?";
        int updateOk = 0;
        try {
            updateOk = queryRunner.update(sql, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updateOk > 0;
    }
}
