package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.HotelDetailInfoDO;
import com.cl.wyn.core.mapper.HotelDetailInfoMapper;
import com.cl.wyn.core.service.IHotelDetailInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 酒店详细信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
@Service
public class HotelDetailInfoServiceImpl extends ServiceImpl<HotelDetailInfoMapper, HotelDetailInfoDO> implements IHotelDetailInfoService {

    @Override
    public void deleteByHotelId(String hotelId) {
        baseMapper.deleteByHotelId(hotelId);
    }
}
