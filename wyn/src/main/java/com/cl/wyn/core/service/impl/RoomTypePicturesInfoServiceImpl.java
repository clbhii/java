package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.RoomTypePicturesInfoDO;
import com.cl.wyn.core.mapper.RoomTypePicturesInfoMapper;
import com.cl.wyn.core.service.IRoomTypePicturesInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 物理房型图片信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
@Service
public class RoomTypePicturesInfoServiceImpl extends ServiceImpl<RoomTypePicturesInfoMapper, RoomTypePicturesInfoDO> implements IRoomTypePicturesInfoService {

    @Override
    public void insertBatch(List<RoomTypePicturesInfoDO> roomTypePicturesInfoDOList) {
        if(roomTypePicturesInfoDOList != null && roomTypePicturesInfoDOList.size() > 0) {
            baseMapper.insertBatch(roomTypePicturesInfoDOList);
        }
    }
}
