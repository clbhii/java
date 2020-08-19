package com.cl.wyn.core.mapper;

import com.cl.wyn.core.entity.RoomTypeSourceInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 酒店物理房型来源信息 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
public interface RoomTypeSourceInfoMapper extends BaseMapper<RoomTypeSourceInfoDO> {

    void insertBatch(List<RoomTypeSourceInfoDO> roomTypeSourceInfoDOList);

    void deleteByRoomTypeId(String roomTypeId);

}
