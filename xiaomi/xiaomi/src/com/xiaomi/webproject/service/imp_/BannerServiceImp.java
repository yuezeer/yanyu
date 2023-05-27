package com.xiaomi.webproject.service.imp_;

import com.xiaomi.webproject.bean.Banner;
import com.xiaomi.webproject.dao.BannerDao;
import com.xiaomi.webproject.dao.imp_.BannerDaoImp;
import com.xiaomi.webproject.service.BannerService;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class BannerServiceImp implements BannerService {
    private BannerDao bannerDao = new BannerDaoImp();
    @Override
    public List<Banner> banners() {
        return bannerDao.banners();
    }
}
