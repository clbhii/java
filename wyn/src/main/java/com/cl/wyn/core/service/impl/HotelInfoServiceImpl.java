package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.HotelInfoDO;
import com.cl.wyn.core.mapper.HotelInfoMapper;
import com.cl.wyn.core.service.IHotelInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 酒店信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-06-28
 */
@Service
public class HotelInfoServiceImpl extends ServiceImpl<HotelInfoMapper, HotelInfoDO> implements IHotelInfoService {

    @Override
    public void deleteByHotelId(String hotelId) {
        baseMapper.deleteByHotelId(hotelId);
    }
}
