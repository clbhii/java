package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * by cl at 2020/6/25 0025
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomAvailableInfo implements Serializable {

    /**
     * 酒店编码
     */
    @JsonProperty("HotelNo")
    private String hotelNo;
    /**
     * 营业日
     */
    @JsonProperty("BizDay")
    private String bizDay;
    /**
     * 房型编码
     */
    @JsonProperty("RoomTypeCode")
    private String roomTypeCode;
    /**
     * 房型名称
     */
    @JsonProperty("RoomTypeName")
    private String roomTypeName;
    /**
     * 可用库存
     */
    @JsonProperty("OrderNum")
    private Integer oderNum;
    /**
     * 状态1：有效，0：无效当状态为0时，渠道应下架当前酒店营业日内对应房型
     */
    @JsonProperty("Status")
    private Integer status;
}
