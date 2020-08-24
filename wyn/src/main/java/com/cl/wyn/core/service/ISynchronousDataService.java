package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.RoomSourceInfoDO;
import org.slf4j.Logger;

import java.util.List;

/**
 * by cl at 2020/6/28 0028
 */
public interface ISynchronousDataService {

    /**
     * 同步区域
     */
    void synDistrictInfo() ;


    /**
     * 同步所有
     */
    void synAll();

    /**
     * 同步所有
     * @param day  日态天数
     */
    void synAll(int day);

    /**
     * 根据酒店ids同步
     * @param hotelIdList
     */
    void synByHotelIds(List<String> hotelIdList);

    /**
     * 根据源酒店ids同步
     * @param sourceHotelIdList
     */
    void synBySourceHotelId(List<String> sourceHotelIdList);


    /**
     * 按时间更新日态价格
     * @param roomSourceInfoDO
     * @param inDate
     * @param outDate
     */
    void synRoomDayPrice(Logger logger, RoomSourceInfoDO roomSourceInfoDO, String inDate, String outDate);
}
