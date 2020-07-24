package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 房型状态
 *
 * by cl at 2020/6/25 0025
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomStatusInfo implements Serializable {
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
     * 门市价
     */
    @JsonProperty("RackRate")
    private String rackRate;
    /**
     * 床位说明
     */
    @JsonProperty("BedDesc")
    private String bedDesc;

    /**
     * 价格计划列表
     */
    @JsonProperty("RatePlanList")
    private List<RatePlanInfo> ratePlanInfoList;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RatePlanInfo implements Serializable{
        /**
         * 市场活动编码
         */
        @JsonProperty("RpId")
        private String rpId;
        /**
         * 实际价格（结算底价）
         */
        @JsonProperty("DisRate")
        private BigDecimal disRate;
        /**
         * 当前剩余房量
         */
        @JsonProperty("OrderNum")
        private Integer orderNum;
    }
}
