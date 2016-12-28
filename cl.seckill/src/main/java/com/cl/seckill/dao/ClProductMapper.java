package com.cl.seckill.dao;

import com.cl.seckill.entity.ClProduct;

public interface ClProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClProduct record);

    int insertSelective(ClProduct record);

    ClProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClProduct record);

    int updateByPrimaryKey(ClProduct record);
}