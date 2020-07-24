package com.cl.wyn.core.adapter.info.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * by cl at 2020/6/25 0025
 */
@Data
public class OrderConfirmBookingParam extends BaseParam{

    /**
     * 渠道凭证
     */
    private String token;
    /**
     * 酒店编号
     */
    @JsonProperty("hotelno")
    private String hotelNo;
    /**
     * 酒店编号
     */
    @JsonProperty("roomtypeno")
    private String roomTypeNo;
    /**
     * 入住时间
     */
    @JsonProperty("indate")
    private String inDate;
    /**
     * 离店时间
     */
    @JsonProperty("outdate")
    private String outDate;
    /**
     * 预定房间数
     */
    @JsonProperty("roomnum")
    private String roomNum;
    /**
     * 订单总价
     */
    @JsonProperty("ordertotalprice")
    private String orderTotalPrice;
    /**
     * 价格计划代码YbRate：贵宾卡价格ZsRate：钻石卡价格ZzRate：至尊钻石卡价格SsRate：绅士卡价格
     */
    @JsonProperty("rateplancode")
    private String ratePlanCode;
    /**
     * 协议编号
     */
    @JsonProperty("agrno")
    private String agrNo;
}
