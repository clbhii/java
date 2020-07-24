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
public class ProvinceCityInfo implements Serializable {
    /**
     * 省份编号
     */
    @JsonProperty("ProvinceNo")
    private String provinceNo;
    /**
     * 省份名称
     */
    @JsonProperty("ProvinceName")
    private String provinceName;
    /**
     * 城市编号
     */
    @JsonProperty("CityNo")
    private String cityNo;
    /**
     * 城市名称
     */
    @JsonProperty("CityName")
    private String cityName;
}
