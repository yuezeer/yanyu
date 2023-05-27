package com.xiaomi.webproject.dao;

import com.xiaomi.webproject.bean.Banner;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public interface BannerDao {
    //查询所有banner数据
    List<Banner> banners();
}
