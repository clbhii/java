package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.RoomInfoDO;
import com.cl.wyn.core.mapper.RoomInfoMapper;
import com.cl.wyn.core.service.IRoomInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * room_info 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfoDO> implements IRoomInfoService {
    @Override
    public void insertBatch(List<RoomInfoDO> roomInfoDOList) {
        if(roomInfoDOList != null && roomInfoDOList.size() > 0) {
            baseMapper.insertBatch(roomInfoDOList);
        }
    }
}
