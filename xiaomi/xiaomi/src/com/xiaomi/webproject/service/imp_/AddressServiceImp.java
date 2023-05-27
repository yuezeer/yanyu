package com.xiaomi.webproject.service.imp_;

import com.xiaomi.webproject.bean.Address;
import com.xiaomi.webproject.dao.AddressDao;
import com.xiaomi.webproject.dao.imp_.AddressDaoImp;
import com.xiaomi.webproject.service.AddressService;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class AddressServiceImp implements AddressService {
    private AddressDao addressDao = new AddressDaoImp();
//添加收货地址
    @Override
    public boolean addressService(Address address) {
        return addressDao.addressService(address);
    }

    @Override
    public List<Address> addressList(String id) {
        return addressDao.addressList(id);
    }
    //设置默认地址
    @Override
    public void setDefault(String id) {
        addressDao.setDefault(id);
    }
    //删除地址
    @Override
    public boolean deleteAddress(String id) {
        return addressDao.deleteAddress(id);
    }
    //根据id查询地址
    @Override
    public Address queryById(String id) {
        return addressDao.queryById(id);
    }
    //修改地址信息
    @Override
    public boolean updateAddress(Address address) {
        return addressDao.updateAddress(address);
    }
}
