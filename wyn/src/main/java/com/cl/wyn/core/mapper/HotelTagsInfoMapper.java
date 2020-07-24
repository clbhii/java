package com.cl.wyn.core.mapper;

import com.cl.wyn.core.entity.HotelTagsInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 酒店标签信息 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
public interface HotelTagsInfoMapper extends BaseMapper<HotelTagsInfoDO> {

    void insertBatch(List<HotelTagsInfoDO> hotelTagsInfoDOList);

}
