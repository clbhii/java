package com.cl.seckill.entity;

import java.math.BigDecimal;

public class ClProduct {
    /**
     * 
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 获取
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取商品价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取商品数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置商品数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }
}