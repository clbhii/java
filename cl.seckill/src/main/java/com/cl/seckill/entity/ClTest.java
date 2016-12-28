package com.cl.seckill.entity;

public class ClTest {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     * 获取
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}