package com.cl.wyn.core.mapper;

import com.cl.wyn.core.entity.HotelExternalFacilitiesInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 酒店外部设施信息 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
public interface HotelExternalFacilitiesInfoMapper extends BaseMapper<HotelExternalFacilitiesInfoDO> {

    void deleteByHotelId(String hotelId);
}
