package com.cl.wyn.core.mapper;

import com.cl.wyn.core.entity.HotelPicturesInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 酒店图片信息 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
public interface HotelPicturesInfoMapper extends BaseMapper<HotelPicturesInfoDO> {

    void insertBatch(List<HotelPicturesInfoDO> hotelPicturesInfoDOList);
    void deleteByHotelId(String hotelId);
}
