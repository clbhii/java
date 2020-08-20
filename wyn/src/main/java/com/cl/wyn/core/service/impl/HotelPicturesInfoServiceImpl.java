package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.HotelPicturesInfoDO;
import com.cl.wyn.core.mapper.HotelPicturesInfoMapper;
import com.cl.wyn.core.service.IHotelPicturesInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 酒店图片信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
@Service
public class HotelPicturesInfoServiceImpl extends ServiceImpl<HotelPicturesInfoMapper, HotelPicturesInfoDO> implements IHotelPicturesInfoService {
    @Override
    public void insertBatch(List<HotelPicturesInfoDO> hotelPicturesInfoDOList) {
        if(hotelPicturesInfoDOList != null && hotelPicturesInfoDOList.size() > 0 )
        baseMapper.insertBatch(hotelPicturesInfoDOList);
    }

    @Override
    public void deleteByHotelId(String hotelId) {
        baseMapper.deleteByHotelId(hotelId);
    }
}
