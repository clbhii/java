package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.RoomTypeInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 物理房型信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
public interface IRoomTypeInfoService extends IService<RoomTypeInfoDO> {
    void insertBatch(List<RoomTypeInfoDO> roomTypeInfoDOList);
}
