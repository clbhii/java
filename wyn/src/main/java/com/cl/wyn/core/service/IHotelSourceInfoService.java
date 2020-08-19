package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.HotelSourceInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 酒店来源信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-06-28
 */
public interface IHotelSourceInfoService extends IService<HotelSourceInfoDO> {
    void deleteByHotelId(String hotelId);
}
