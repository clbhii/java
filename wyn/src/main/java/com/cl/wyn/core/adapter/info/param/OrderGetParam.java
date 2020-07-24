package com.cl.wyn.core.adapter.info.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * by cl at 2020/6/27 0027
 */
@Data
public class OrderGetParam extends  BaseParam{
    /**
     * 渠道凭证
     */
    private String token;
    /**
     * 渠道订单编号
     */
    @JsonProperty("outFlagNo")
    private String outFlagNo;
    /**
     * 订单编号
     */
    @JsonProperty("orderNumber")
    private String orderNumber;
    /**
     * 协议编号
     */
    @JsonProperty("argno")
    private String argNo;
}
