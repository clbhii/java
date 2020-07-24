package com.cl.wyn.core.adapter;

import com.cl.wyn.core.WynApplication;
import com.cl.wyn.core.adapter.IRoomAdapter;
import com.cl.wyn.core.adapter.info.RoomAvailableInfo;
import com.cl.wyn.core.adapter.info.RoomStatusInfo;
import com.cl.wyn.core.adapter.info.param.RoomAvailableParam;
import com.cl.wyn.core.adapter.info.param.RoomStatusParam;
import com.cl.wyn.core.enums.RatePlanEnum;
import com.cl.wyn.core.util.common.DateUtil;
import com.cl.wyn.core.util.json.JacksonBuilder;
import com.cl.wyn.core.util.json.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * by cl at 2020/6/25 0025
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WynApplication.class)
@Slf4j
public class RoomAdapterTest {
    @Autowired
    private IRoomAdapter roomAdapter;

    private ObjectMapper objectMapper = new JacksonBuilder().setSerializationInclusion(JsonInclude.Include.NON_NULL).build();
    @Test
    public void roomStatus() {
        RoomStatusParam param = new RoomStatusParam();
        param.setHotelNo("5180481");
////        param.setRoomTypeNo("DK");
        Date date = new Date();
        param.setInDate(DateUtil.format(date, DateUtil.DEFAULT_DATE));
        param.setOutDate(DateUtil.format(DateUtil.addDay(date, 60), DateUtil.DEFAULT_DATE));
        param.setRatePlanList(RatePlanEnum.DisRate.getValue());
        List<RoomStatusInfo> roomStatusInfos = roomAdapter.roomStatus(param);
        log.info(JacksonUtils.objectToJson(objectMapper, roomStatusInfos));
    }

    @Test
    public void roomAvailable() {
        RoomAvailableParam param = new RoomAvailableParam();
        param.setHotelNo("5180481");
        param.setInDate("2020-06-25");
        param.setOutDate("2020-06-29");
        List<RoomAvailableInfo> roomAvailableInfoList = roomAdapter.roomAvailable(param);
        log.info(JacksonUtils.objectToJson(objectMapper, roomAvailableInfoList));
    }

    @Test
    public void roomAvailableUp() {
        RoomAvailableParam param = new RoomAvailableParam();
        param.setHotelNo("5180481");
        param.setInDate("2020-06-25");
        param.setOutDate("2020-06-29");
        param.setLastTime("2020-01-26 12:00:00");
        List<RoomAvailableInfo> roomAvailableInfoList = roomAdapter.roomAvailableUp(param);
        log.info(JacksonUtils.objectToJson(objectMapper, roomAvailableInfoList));
    }
}
;