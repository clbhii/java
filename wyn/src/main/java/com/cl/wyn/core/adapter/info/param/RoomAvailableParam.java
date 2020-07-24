package com.cl.wyn.core.adapter.info.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * by cl at 2020/6/25 0025
 */
@Data
public class RoomAvailableParam extends BaseParam{

    /**
     * 酒店编号必须是7位
     */
    @JsonProperty("hotelno")
    private String hotelNo;
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
     * 从此时间点起获取增量的数据 2019-02-27 12:00:00
     *  相对于更新时间
     */
    @JsonProperty("lasttime")
    private String lastTime;

}
