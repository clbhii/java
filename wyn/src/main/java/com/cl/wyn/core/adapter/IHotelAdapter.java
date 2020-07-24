package com.cl.wyn.core.adapter;

import com.cl.wyn.core.adapter.info.*;
import com.cl.wyn.core.adapter.info.param.*;

import java.util.List;

public interface IHotelAdapter {

    /**
     * 获取酒店信息
     * @param param
     * @return
     */
    HotelInfo getModel(HotelGetModelParam param);

    /**
     * 获取酒店列表
     * @param param
     * @return
     */
    PageList<HotelInfo> getList(HotelGetListParam param);

    /**
     * 获取酒店图片列表
     * @param param
     * @return
     */
    List<HotelPicInfo> getPicList(HotelGetPicListParam param);

    /**
     * 获取房型列表
     * @param param
     * @return
     */
    List<RoomTypeInfo> getRoomType(HotelGetRoomTypeParam param);

    /**
     * 获取房型图片列表
     * @param param
     * @return
     */
    List<RoomTypePicInfo> getRoomTypePic(HotelGetRoomTypePicParam param);
}
