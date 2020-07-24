package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.RoomSourceInfoDO;
import com.cl.wyn.core.mapper.RoomSourceInfoMapper;
import com.cl.wyn.core.service.IRoomSourceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 酒店售卖房型来源信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
@Service
public class RoomSourceInfoServiceImpl extends ServiceImpl<RoomSourceInfoMapper, RoomSourceInfoDO> implements IRoomSourceInfoService {
    @Override
    public void insertBatch(List<RoomSourceInfoDO> roomSourceInfoDOList) {
        if(roomSourceInfoDOList != null && roomSourceInfoDOList.size() >0) {
            baseMapper.insertBatch(roomSourceInfoDOList);
        }
    }
}
