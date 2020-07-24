package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * by cl at 2020/6/26 0026
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public  class OrderActualInfo implements Serializable {

    /**
     * 入住时间
     */
    @JsonProperty("InDate")
    private String inDate;
    /**
     * 离店时间
     */
    @JsonProperty("OutDate")
    private String outDate;
    /**
     * 入住订单状态
     */
    @JsonProperty("RoomOrderStatus")
    private String roomOrderStatus;
    /**
     * 房间号
     */
    @JsonProperty("RoomNo")
    private String roomNo;
    /**
     * 入住人
     */
    @JsonProperty("GuestName")
    private String guestName;
}
