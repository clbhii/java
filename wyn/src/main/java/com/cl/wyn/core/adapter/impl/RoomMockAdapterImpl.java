package com.cl.wyn.core.adapter.impl;

import com.cl.wyn.core.adapter.IRoomAdapter;
import com.cl.wyn.core.adapter.info.BaseResult;
import com.cl.wyn.core.adapter.info.RoomAvailableInfo;
import com.cl.wyn.core.adapter.info.RoomStatusInfo;
import com.cl.wyn.core.adapter.info.param.RoomAvailableParam;
import com.cl.wyn.core.adapter.info.param.RoomStatusParam;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * by cl at 2020/6/25 0025
 */
@Component("roomMockAdapter")
public class RoomMockAdapterImpl extends AbsAdapter implements IRoomAdapter {

    @Value("${wyn.api.room.roomStatusPath}")
    private String roomStatusPath;
    @Value("${wyn.api.room.roomAvailablePath}")
    private String roomAvailablePath;
    @Value("${wyn.api.room.roomAvailableUpPath}")
    private String roomAvailableUpPath;

    @Override
    public List<RoomStatusInfo> roomStatus(RoomStatusParam param) {
        param.setHotelNo("5180481");
        List<RoomStatusInfo> roomStatusInfoList = post(roomStatusPath, param, new TypeReference<BaseResult<List<RoomStatusInfo>>>() {});
        if(roomStatusInfoList == null) {
            roomStatusInfoList = new ArrayList<>();
        }
        return roomStatusInfoList;
    }

    @Override
    public List<RoomAvailableInfo> roomAvailable(RoomAvailableParam param) {
        return post(roomAvailablePath, param, new TypeReference<BaseResult<List<RoomAvailableInfo>>>() {});
    }

    @Override
    public List<RoomAvailableInfo> roomAvailableUp(RoomAvailableParam param) {
        return post(roomAvailableUpPath, param, new TypeReference<BaseResult<List<RoomAvailableInfo>>>() {});
    }
}
