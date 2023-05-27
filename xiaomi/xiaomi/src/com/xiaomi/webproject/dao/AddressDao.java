package com.xiaomi.webproject.dao;

import com.xiaomi.webproject.bean.Address;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public interface AddressDao {
    //添加收货地址
    boolean addressService(Address address);
    //查询当前用户收获地址
    List<Address> addressList(String id);
    //设置默认地址
    void setDefault(String id);
    //删除地址
    boolean deleteAddress(String id);
    //根据id查询地址
    Address queryById(String id);
    //修改地址信息
    boolean updateAddress(Address address);
}
