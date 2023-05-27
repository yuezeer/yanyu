package com.xiaomi.webproject.dao.imp_;

import com.xiaomi.webproject.bean.Banner;
import com.xiaomi.webproject.dao.BannerDao;
import com.xiaomi.webproject.util.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class BannerDaoImp implements BannerDao {
    private QueryRunner qr = new QueryRunner(JDBCUtilsByDruid.getDataSource());
    @Override
    public List<Banner> banners() {
        String sql = "select * from tb_banner";
        try {
            List<Banner> banners = qr.query(sql, new BeanListHandler<>(Banner.class));
            return banners;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
