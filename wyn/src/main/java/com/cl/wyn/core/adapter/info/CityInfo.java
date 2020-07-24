package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * by cl at 2020/6/25 0025
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityInfo implements Serializable {

    /**
     * 城市代码
     */
    @JsonProperty("CityCode")
    private String cityCode;
    /**
     * 城市名称
     */
    @JsonProperty("CityName")
    private String cityName;
    /**
     * 城市编号
     */
    @JsonProperty("CityNo")
    private String cityNo;
    /**
     * 地区集合
     */
    @JsonProperty("AreaList")
    private List<String> areaList;
    /**
     * 商圈集合
     */
    @JsonProperty("SQList")
    private List<String> sqList;
}
