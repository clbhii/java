package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.HotelExternalFacilitiesInfoDO;
import com.cl.wyn.core.mapper.HotelExternalFacilitiesInfoMapper;
import com.cl.wyn.core.service.IHotelExternalFacilitiesInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 酒店外部设施信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
@Service
public class HotelExternalFacilitiesInfoServiceImpl extends ServiceImpl<HotelExternalFacilitiesInfoMapper, HotelExternalFacilitiesInfoDO> implements IHotelExternalFacilitiesInfoService {

    @Override
    public void deleteByHotelId(String hotelId) {
        baseMapper.deleteByHotelId(hotelId);
    }
}
