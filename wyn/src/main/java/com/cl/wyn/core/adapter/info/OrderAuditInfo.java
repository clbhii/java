package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * by cl at 2020/6/26 0026
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderAuditInfo implements Serializable {
    /**
     * 酒店编号
     */
    @JsonProperty("HotelNo")
    private String hotelNo;
    /**
     * 酒店名称
     */
    @JsonProperty("HotelName")
    private String hotelName;
    /**
     * 订单编号
     */
    @JsonProperty("OrderNo")
    private String orderNo;
    /**
     * 订单状态
     */
    @JsonProperty("OrderStatus")
    private String orderStatus;
    /**
     * 订单状态描述
     */
    @JsonProperty("OrderStatusText")
    private String orderStatusText;

    @JsonProperty("Rooms")
    private List<OrderActualInfo> orderActualInfo;
}
