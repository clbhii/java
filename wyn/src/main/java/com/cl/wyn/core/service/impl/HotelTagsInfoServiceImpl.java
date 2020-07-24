package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.HotelTagsInfoDO;
import com.cl.wyn.core.mapper.HotelTagsInfoMapper;
import com.cl.wyn.core.service.IHotelTagsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 酒店标签信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
@Service
public class HotelTagsInfoServiceImpl extends ServiceImpl<HotelTagsInfoMapper, HotelTagsInfoDO> implements IHotelTagsInfoService {
    @Override
    public void insertBatch(List<HotelTagsInfoDO> hotelTagsInfoDOList) {
        if(hotelTagsInfoDOList != null && hotelTagsInfoDOList.size() > 0)
        baseMapper.insertBatch(hotelTagsInfoDOList);
    }
}
