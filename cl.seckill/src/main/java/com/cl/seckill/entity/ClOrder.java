package com.cl.seckill.entity;

public class ClOrder {
    /**
     * 
     */
    private Integer id;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 状态(1：下单，2：付款)
     */
    private Integer status;

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
     * 获取商品id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置商品id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取状态(1：下单，2：付款)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态(1：下单，2：付款)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}