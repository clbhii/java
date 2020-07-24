package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.HotelTagsInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 酒店标签信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
public interface IHotelTagsInfoService extends IService<HotelTagsInfoDO> {
    void insertBatch(List<HotelTagsInfoDO> hotelTagsInfoDOList);
}
