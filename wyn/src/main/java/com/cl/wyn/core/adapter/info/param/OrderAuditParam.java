package com.cl.wyn.core.adapter.info.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * by cl at 2020/6/26 0026
 */
@Data
public class OrderAuditParam extends BaseParam{

    /**
     * 渠道凭证
     */
    private String token;
    /**
     * 订单编号
     */
    @JsonProperty("orderno")
    private String orderNo;
    /**
     * 协议编号
     */
    @JsonProperty("argno")
    private String argNo;
}
