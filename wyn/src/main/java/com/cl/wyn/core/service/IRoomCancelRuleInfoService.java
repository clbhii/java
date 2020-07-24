package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.RoomCancelRuleInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 酒店售卖房型取消规则信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-07-15
 */
public interface IRoomCancelRuleInfoService extends IService<RoomCancelRuleInfoDO> {

    void insertBatch(List<RoomCancelRuleInfoDO> roomCancelRuleInfoDOList);
}
