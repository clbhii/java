package com.cl.wyn.core.adapter;

import com.cl.wyn.core.adapter.info.RoomAvailableInfo;
import com.cl.wyn.core.adapter.info.RoomStatusInfo;
import com.cl.wyn.core.adapter.info.param.RoomAvailableParam;
import com.cl.wyn.core.adapter.info.param.RoomStatusParam;

import java.util.List;

/**
 * by cl at 2020/6/25 0025
 */
public interface IRoomAdapter {
    /**
     * 房态接口
     * @param param
     * @return
     */
    List<RoomStatusInfo> roomStatus(RoomStatusParam param);

    /**
     * 获取指定日期区间内房量
     * @param param
     * @return
     */
    List<RoomAvailableInfo> roomAvailable(RoomAvailableParam param);

    /**
     * 增量获取指定日期区间内房量
     * @param param
     * @return
     */
    List<RoomAvailableInfo> roomAvailableUp(RoomAvailableParam param);
}
