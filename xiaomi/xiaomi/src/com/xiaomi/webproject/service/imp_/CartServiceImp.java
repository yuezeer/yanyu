package com.xiaomi.webproject.service.imp_;

import com.xiaomi.webproject.bean.CartItem;
import com.xiaomi.webproject.dao.CartDao;
import com.xiaomi.webproject.dao.imp_.CartDaoImp;
import com.xiaomi.webproject.service.CartService;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class CartServiceImp implements CartService {
    private CartDao cartDao = new CartDaoImp();
    //查询当前用户购物条目
    @Override
    public List<CartItem> CartItems(String uid) {
        return cartDao.CartItems(uid);
    }
    //修改购物车条目
    @Override
    public void updateCart(CartItem cartItem) {
        cartDao.updateCart(cartItem);
    }
    //添加购物车条目
    @Override
    public void addCartItem(CartItem cartItem) {
        cartDao.addCartItem(cartItem);
    }

    @Override
    public boolean deleteCartItem(String cid) {
        return cartDao.deleteCartItem(cid);
    }

    @Override
    public boolean clearCart(String uid) {
        return cartDao.clearCart(uid);
    }
}
