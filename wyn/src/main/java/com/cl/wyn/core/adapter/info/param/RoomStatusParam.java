package com.cl.wyn.core.adapter.info.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * by cl at 2020/6/25 0025
 */
@Data
public class RoomStatusParam extends BaseParam{

    /**
     * 酒店编号必须是7位
     */
    @JsonProperty("hotelno")
    private String hotelNo;
    /**
     * 房型编号
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
     * 展示价格计划DisRate:渠道协议价YbRate:贵宾卡（会员）价格ZsRate:钻石卡价格ZzRate:至尊卡价格SsRate:绅士卡价格MarketRate:特价(多种用英文逗号隔开默认渠道协议价会员价)
     */
    @JsonProperty("rateplanlist")
    private String ratePlanList;
    /**
     * 协议编号
     */
    @JsonProperty("agrno")
    private String agrNo;

}
