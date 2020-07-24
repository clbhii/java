package com.cl.wyn.core.adapter.info.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * by cl at 2020/6/24 0024
 */
@Data
public class HotelGetRoomTypeParam extends BaseParam{
    /**
     * 酒店编号
     */
    @JsonProperty("hotelno")
    private String hotelNo;
}
