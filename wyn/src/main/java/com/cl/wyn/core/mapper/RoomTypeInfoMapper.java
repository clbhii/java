package com.cl.wyn.core.mapper;

import com.cl.wyn.core.adapter.info.RoomTypeInfo;
import com.cl.wyn.core.entity.RoomTypeInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 物理房型信息 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
public interface RoomTypeInfoMapper extends BaseMapper<RoomTypeInfoDO> {

    void insertBatch(List<RoomTypeInfoDO> roomTypeInfoDOList);
}
