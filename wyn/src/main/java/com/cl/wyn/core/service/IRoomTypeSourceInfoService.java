package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.RoomTypeSourceInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 酒店物理房型来源信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
public interface IRoomTypeSourceInfoService extends IService<RoomTypeSourceInfoDO> {

    void insertBatch(List<RoomTypeSourceInfoDO> roomTypeSourceInfoDOList);

    void deleteByRoomTypeId(String roomTypeId);

}
