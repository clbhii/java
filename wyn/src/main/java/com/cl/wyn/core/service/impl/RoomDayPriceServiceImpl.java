package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.RoomDayPriceDO;
import com.cl.wyn.core.mapper.RoomDayPriceMapper;
import com.cl.wyn.core.service.IRoomDayPriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 售卖房型日态价格信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
@Service
public class RoomDayPriceServiceImpl extends ServiceImpl<RoomDayPriceMapper, RoomDayPriceDO> implements IRoomDayPriceService {

    @Override
    public void insertBatch(List<RoomDayPriceDO> roomDayPriceDOList) {
        if(roomDayPriceDOList != null && roomDayPriceDOList.size() > 0)
        baseMapper.insertBatch(roomDayPriceDOList);
    }
}
