package com.cl.wyn.core.mapper;

import com.cl.wyn.core.entity.RoomTypePicturesInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 物理房型图片信息 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
public interface RoomTypePicturesInfoMapper extends BaseMapper<RoomTypePicturesInfoDO> {

    void insertBatch(List<RoomTypePicturesInfoDO> roomTypePicturesInfoDOList);

}
