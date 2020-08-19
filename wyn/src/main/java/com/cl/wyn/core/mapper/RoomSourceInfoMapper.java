package com.cl.wyn.core.mapper;

import com.cl.wyn.core.entity.RoomSourceInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 酒店售卖房型来源信息 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
public interface RoomSourceInfoMapper extends BaseMapper<RoomSourceInfoDO> {

    void insertBatch(List<RoomSourceInfoDO> roomSourceInfoDOList);

    void deleteByRoomId(String roomId);

}
