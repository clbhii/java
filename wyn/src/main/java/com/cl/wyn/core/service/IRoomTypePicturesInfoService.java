package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.RoomTypePicturesInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 物理房型图片信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
public interface IRoomTypePicturesInfoService extends IService<RoomTypePicturesInfoDO> {
    void insertBatch(List<RoomTypePicturesInfoDO> roomTypePicturesInfoDOList);

    void deleteByRoomTypeId(String roomTypeId);
}
