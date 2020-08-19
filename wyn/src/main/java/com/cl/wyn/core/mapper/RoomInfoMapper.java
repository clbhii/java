package com.cl.wyn.core.mapper;

import com.cl.wyn.core.entity.RoomInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * room_info Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
public interface RoomInfoMapper extends BaseMapper<RoomInfoDO> {

    void insertBatch(List<RoomInfoDO> roomInfoDOList);

    void deleteByRoomId(String roomId);

}
