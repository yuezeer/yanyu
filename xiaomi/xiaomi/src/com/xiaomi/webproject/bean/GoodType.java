package com.xiaomi.webproject.bean;

import java.util.List;

/**
 * @author 陈靖文
 * @version 1.0
 */
public class GoodType {
    private String id;
    private String name;
    private List<Goods> goods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
}
