package com.cl.wyn.core.mapper;

import com.cl.wyn.core.entity.RoomDayPriceDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 售卖房型日态价格信息 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
public interface RoomDayPriceMapper extends BaseMapper<RoomDayPriceDO> {

    void insertBatch(List<RoomDayPriceDO> roomDayPriceDOList);

    void deleteByRoomId(String roomId);
}
