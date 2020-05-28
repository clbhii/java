package com.cl.dao;


import com.cl.model.DeviceDO;

import java.util.List;
import java.util.Map;

public interface DeviceDAO {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceDO record);

    DeviceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DeviceDO record);

    List<DeviceDO> selectList(Map<String, Object> map);

    int countList(Map<String, Object> map);
}