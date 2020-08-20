package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.RoomCancelRuleInfoDO;
import com.cl.wyn.core.mapper.RoomCancelRuleInfoMapper;
import com.cl.wyn.core.service.IRoomCancelRuleInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 酒店售卖房型取消规则信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-07-15
 */
@Service
public class RoomCancelRuleInfoServiceImpl extends ServiceImpl<RoomCancelRuleInfoMapper, RoomCancelRuleInfoDO> implements IRoomCancelRuleInfoService {

    @Override
    public void insertBatch(List<RoomCancelRuleInfoDO> roomCancelRuleInfoDOList) {
        if(roomCancelRuleInfoDOList != null && roomCancelRuleInfoDOList.size() > 0)
        super.baseMapper.insertBatch(roomCancelRuleInfoDOList);
    }

    @Override
    public void deleteByRoomId(String roomId) {
        baseMapper.deleteByRoomId(roomId);
    }
}
