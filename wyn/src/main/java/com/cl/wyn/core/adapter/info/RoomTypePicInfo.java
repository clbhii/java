package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * by cl at 2020/6/24 0024
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomTypePicInfo implements Serializable {
    /**
     * 酒店编号
     */
    @JsonProperty("HotelNo")
    private String hotelNo;
    /**
     * 豪华单人房
     */
    @JsonProperty("RoomTypeNo")
    private String roomTypeNo;
    /**
     * 大图
     */
    @JsonProperty("BigImageUrl")
    private String bigImageUrl;
    /**
     * 中图
     */
    @JsonProperty("MidImageUrl")
    private String midImageUrl;
    /**
     * 小图
     */
    @JsonProperty("SmallImageUrl")
    private String smallImageUrl;
}
