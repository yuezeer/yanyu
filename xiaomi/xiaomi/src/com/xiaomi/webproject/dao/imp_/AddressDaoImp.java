package com.xiaomi.webproject.dao.imp_;

import com.xiaomi.webproject.bean.Address;
import com.xiaomi.webproject.dao.AddressDao;
import com.xiaomi.webproject.util.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class AddressDaoImp implements AddressDao {
    private QueryRunner qr = new QueryRunner(JDBCUtilsByDruid.getDataSource());
//添加收货地址
    @Override
    public boolean addressService(Address address) {
        String sql = "insert into tb_address(province,city,region," +
                "detail,receiver,phone,uid,level,createtime) values" +
                "(?,?,?,?,?,?,?,?,?)";
        try {
            int update = qr.update(sql,address.getProvince(), address.getCity(), address.getRegion()
                    , address.getDetail(), address.getReceiver(), address.getPhone()
                    , address.getUid(), address.getLevel(), address.getCreatetime());
            return update > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //查询当前用户收获地址
    @Override
    public List<Address> addressList(String id) {
        String sql = "select * from tb_address where uid=?";
        try {
            List<Address> addresses = qr.query(sql, new BeanListHandler<>(Address.class), id);
            return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //设置默认地址
    @Override
    public void setDefault(String id) {
        String sql = "update tb_address set level=1 where id=?";
        try {
            qr.update(sql,id);
            sql = "update tb_address set level=0 where id <> ?";
            qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //删除地址信息
    @Override
    public boolean deleteAddress(String id) {
        String sql = "delete from tb_address where id=?";
        try {
            int update = qr.update(sql, id);
            return update > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
    //根据id查询地址
    @Override
    public Address queryById(String id) {
        String sql = "select * from tb_address where id=?";
        try {
            Address address = qr.query(sql, new BeanHandler<>(Address.class), id);
            return address;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    //修改地址信息
    @Override
    public boolean updateAddress(Address address) {
        String sql = "update tb_address set province=?,city=?,region=?," +
                "detail=?,receiver=?,phone=? where id=?";
        try {
            int update = qr.update(sql, address.getProvince(), address.getCity(), address.getRegion(),
                    address.getDetail(), address.getReceiver(), address.getPhone()
                    , address.getId());
            return update > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
