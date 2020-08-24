package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.HotelExternalFacilitiesInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 酒店外部设施信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
public interface IHotelExternalFacilitiesInfoService extends IService<HotelExternalFacilitiesInfoDO> {

    void deleteByHotelId(String hotelId);
}
