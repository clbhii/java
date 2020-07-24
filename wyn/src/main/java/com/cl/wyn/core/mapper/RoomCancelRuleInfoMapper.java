package com.cl.wyn.core.mapper;

import com.cl.wyn.core.entity.RoomCancelRuleInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 酒店售卖房型取消规则信息 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-07-15
 */
public interface RoomCancelRuleInfoMapper extends BaseMapper<RoomCancelRuleInfoDO> {

    void insertBatch(List<RoomCancelRuleInfoDO> roomCancelRuleInfoDOList);
}
