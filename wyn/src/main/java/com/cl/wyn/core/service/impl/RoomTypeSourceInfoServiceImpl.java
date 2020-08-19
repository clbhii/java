package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.RoomTypeSourceInfoDO;
import com.cl.wyn.core.mapper.RoomTypeSourceInfoMapper;
import com.cl.wyn.core.service.IRoomTypeSourceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 酒店物理房型来源信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
@Service
public class RoomTypeSourceInfoServiceImpl extends ServiceImpl<RoomTypeSourceInfoMapper, RoomTypeSourceInfoDO> implements IRoomTypeSourceInfoService {

    @Override
    public void insertBatch(List<RoomTypeSourceInfoDO> roomTypeSourceInfoDOList) {
        if(roomTypeSourceInfoDOList != null && roomTypeSourceInfoDOList.size() > 0) {
            baseMapper.insertBatch(roomTypeSourceInfoDOList);
        }
    }

    @Override
    public void deleteByRoomTypeId(String roomTypeId) {
        baseMapper.deleteByRoomTypeId(roomTypeId);
    }
}
