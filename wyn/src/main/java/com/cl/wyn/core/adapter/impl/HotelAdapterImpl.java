package com.cl.wyn.core.adapter.impl;

import com.cl.wyn.core.adapter.IHotelAdapter;
import com.cl.wyn.core.adapter.info.*;
import com.cl.wyn.core.adapter.info.param.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * by cl at 2020/6/24 0024
 */
@Component("hotelAdapter")
public class HotelAdapterImpl extends AbsAdapter implements IHotelAdapter {

    @Value("${wyn.api.hotel.getModelPath}")
    private String getModelPath;
    @Value("${wyn.api.hotel.getListPath}")
    private String getListPath;
    @Value("${wyn.api.hotel.getPicListPath}")
    private String getPicListPath;
    @Value("${wyn.api.hotel.getRoomTypePath}")
    private String getRoomTypePath;
    @Value("${wyn.api.hotel.getRoomTypePicPath}")
    private String getRoomTypePicPath;
    @Override
    public HotelInfo getModel(HotelGetModelParam param) {
        return post(getModelPath, param, new TypeReference<BaseResult<HotelInfo>>() {});
    }

    @Override
    public PageList<HotelInfo> getList(HotelGetListParam param) {
        return post(getListPath, param, new TypeReference<BaseResult<PageList<HotelInfo>>>() {});
    }

    @Override
    public List<HotelPicInfo> getPicList(HotelGetPicListParam param) {
        return post(getPicListPath, param, new TypeReference<BaseResult<List<HotelPicInfo>>>() {});
    }

    @Override
    public List<RoomTypeInfo> getRoomType(HotelGetRoomTypeParam param) {
        return post(getRoomTypePath, param, new TypeReference<BaseResult<List<RoomTypeInfo>>>() {});
    }

    @Override
    public List<RoomTypePicInfo> getRoomTypePic(HotelGetRoomTypePicParam param) {
        return post(getRoomTypePicPath, param, new TypeReference<BaseResult<List<RoomTypePicInfo>>>() {});
    }
}
