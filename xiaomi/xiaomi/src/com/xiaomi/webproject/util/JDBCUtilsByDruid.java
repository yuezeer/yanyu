package com.xiaomi.webproject.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 陈靖文
 * @version 1.0
 * 基于druid数据库连接池的工具类
 */
public class JDBCUtilsByDruid {
    private static DataSource ds;
    //在静态代码块完成ds的初始化
    static{
        try {
            InputStream inputStream =
                    JDBCUtilsByDruid.class.getClassLoader().
                            getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //编写getConnection方法
    public static Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
    }
    //关闭连接,在数据库连接池技术中，close不是真的断掉连接
    //而是把使用的Connection对象放回连接池中
    public static void close(ResultSet resultSet , Statement statement , Connection connection){
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static DataSource getDataSource(){
        return ds;
    }
}
