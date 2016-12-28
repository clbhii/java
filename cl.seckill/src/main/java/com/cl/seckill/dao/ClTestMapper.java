package com.cl.seckill.dao;

import com.cl.seckill.entity.ClTest;

public interface ClTestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClTest record);

    int insertSelective(ClTest record);

    ClTest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClTest record);

    int updateByPrimaryKey(ClTest record);
}