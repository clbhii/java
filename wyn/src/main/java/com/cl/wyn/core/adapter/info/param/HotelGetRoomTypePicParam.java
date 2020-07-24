package com.cl.wyn.core.adapter.info.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * by cl at 2020/6/24 0024
 */
@Data
public class HotelGetRoomTypePicParam extends BaseParam{
    /**
     * 酒店编号
     */
    @JsonProperty("hotelno")
    private String hotelNo;
    /**
     * 房型编号
     */
    @JsonProperty("roomtypeno")
    private String roomTypeNo;
}
