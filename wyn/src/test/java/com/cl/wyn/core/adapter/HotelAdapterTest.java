package com.cl.wyn.core.adapter;

import com.cl.wyn.core.WynApplication;
import com.cl.wyn.core.adapter.IHotelAdapter;
import com.cl.wyn.core.adapter.info.*;
import com.cl.wyn.core.adapter.info.param.*;
import com.cl.wyn.core.util.common.StringUtil;
import com.cl.wyn.core.util.json.JacksonBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * by cl at 2020/6/24 0024
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WynApplication.class)
@Slf4j
public class HotelAdapterTest {
    @Autowired
    private IHotelAdapter hotelAdapter;

    private ObjectMapper objectMapper =  new JacksonBuilder().setSerializationInclusion(JsonInclude.Include.NON_NULL).build();;
    @Test
    public void getModel() throws JsonProcessingException {
        HotelGetModelParam hotelGetModelParam = new HotelGetModelParam();
        hotelGetModelParam.setHotelCode("5180331");
        HotelInfo hotelInfo = hotelAdapter.getModel(hotelGetModelParam);
        log.info(objectMapper.writeValueAsString(hotelInfo));
    }
    @Test
    public void getList() throws JsonProcessingException{
        HotelGetListParam param = new HotelGetListParam();
        Integer pageSize = 500;
        param.setPageSize(pageSize + "");
        PageList<HotelInfo> pageList = hotelAdapter.getList(param);
        List<HotelInfo> hotelInfoList = pageList.getRows();
        int total =  pageList.getTotal();
        for(int i = 0; i < total/pageSize; i++){
            param.setPageIndex( (i + 2) + "");
            pageList = hotelAdapter.getList(param);
            hotelInfoList.addAll(pageList.getRows());
        }
        for(int i = 0; i < hotelInfoList.size() ; i ++) {
            if(StringUtil.isEmpty(hotelInfoList.get(i).getHotelNo())){
                log.info(i + "");
            }
        }
//        log.info(objectMapper.writeValueAsString());
    }
    @Test
    public void getPicList() throws JsonProcessingException{
        HotelGetPicListParam picListParam = new HotelGetPicListParam();
        picListParam.setHotelCode("5180481");
        List<HotelPicInfo> picList = hotelAdapter.getPicList(picListParam);
        log.info(objectMapper.writeValueAsString(picList));
    }
    @Test
    public void getRoomType() throws JsonProcessingException{
        HotelGetRoomTypeParam param = new HotelGetRoomTypeParam();
        param.setHotelNo("5180331");
        List<RoomTypeInfo> roomType = hotelAdapter.getRoomType(param);
        log.info(objectMapper.writeValueAsString(roomType));
    }
    @Test
    public void getRoomTypePic()  throws JsonProcessingException{
        HotelGetRoomTypePicParam param = new HotelGetRoomTypePicParam();
        param.setHotelNo("5180481");
        param.setRoomTypeNo("DK");
        List<RoomTypePicInfo> roomTypePic = hotelAdapter.getRoomTypePic(param);
        log.info(objectMapper.writeValueAsString(roomTypePic));
    }
}
