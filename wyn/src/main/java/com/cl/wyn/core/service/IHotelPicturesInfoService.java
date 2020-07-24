package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.HotelPicturesInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 酒店图片信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
public interface IHotelPicturesInfoService extends IService<HotelPicturesInfoDO> {

    void insertBatch(List<HotelPicturesInfoDO> hotelPicturesInfoDOList);

}
