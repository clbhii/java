package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.RoomDayPriceDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 售卖房型日态价格信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
public interface IRoomDayPriceService extends IService<RoomDayPriceDO> {

    void insertBatch(List<RoomDayPriceDO> roomDayPriceDOList);

    void deleteByRoomId(String roomId);
}
