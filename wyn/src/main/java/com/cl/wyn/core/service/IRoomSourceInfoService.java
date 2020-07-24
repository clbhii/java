package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.RoomSourceInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 酒店售卖房型来源信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
public interface IRoomSourceInfoService extends IService<RoomSourceInfoDO> {
    void insertBatch(List<RoomSourceInfoDO> roomSourceInfoDOList);
}
