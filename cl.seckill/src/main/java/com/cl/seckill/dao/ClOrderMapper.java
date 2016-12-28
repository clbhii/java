package com.cl.seckill.dao;

import java.util.Map;

import com.cl.seckill.entity.ClOrder;

public interface ClOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClOrder record);

    ClOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClOrder record);

    int updateByPrimaryKey(ClOrder record);
    
    int countByCriteria(Map<String, Object> queryMap);
    
    int countFroUpdateByCriteria(Map<String, Object> queryMap);
    
}