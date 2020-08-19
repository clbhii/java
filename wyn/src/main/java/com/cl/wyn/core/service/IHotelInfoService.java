package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.HotelInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 酒店信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-06-28
 */
public interface IHotelInfoService extends IService<HotelInfoDO> {
    void deleteByHotelId(String hotelId);
}
