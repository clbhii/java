package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.RoomTypeInfoDO;
import com.cl.wyn.core.mapper.RoomTypeInfoMapper;
import com.cl.wyn.core.service.IRoomTypeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 物理房型信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
@Service
public class RoomTypeInfoServiceImpl extends ServiceImpl<RoomTypeInfoMapper, RoomTypeInfoDO> implements IRoomTypeInfoService {

    @Override
    public void insertBatch(List<RoomTypeInfoDO> roomTypeInfoDOList) {
        if(roomTypeInfoDOList != null && roomTypeInfoDOList.size() > 0) {
            baseMapper.insertBatch(roomTypeInfoDOList);
        }
    }
}
