package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * by cl at 2020/6/25 0025
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyQuotaInfo implements Serializable {

    /**
     * 日期
     */
    private String date;

    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 结算价
     */
    @JsonProperty("disrate")
    private BigDecimal disRate;
    /**
     * 限定
     */
    private Integer quota;
    /**
     * 活动id
     */
    @JsonProperty("marketid")
    private String marketId;
}


