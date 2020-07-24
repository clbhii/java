package com.cl.wyn.core.adapter.info.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * by cl at 2020/6/24 0024
 */
@Data
public class HotelGetListParam extends BaseParam{
    /**
     * 页码
     */
    @JsonProperty("pageIndex")
    private String pageIndex;
    /**
     * 每页显示数
     */
    @JsonProperty("pageSize")
    private String pageSize;
    /**
     * 开始时间
     */
    @JsonProperty("begintime")
    private String beginTime;
    /**
     * 结束时间
     */
    @JsonProperty("endtime")
    private String endTime;
}
