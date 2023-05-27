package com.xiaomi.webproject.service;

import com.xiaomi.webproject.bean.Banner;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public interface BannerService {
    //查询所有banner数据
    List<Banner> banners();
}
