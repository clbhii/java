package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.RoomInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * room_info 服务类
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
public interface IRoomInfoService extends IService<RoomInfoDO> {
    void insertBatch(List<RoomInfoDO> roomInfoDOList);
    void deleteByRoomId(String roomId);
}
