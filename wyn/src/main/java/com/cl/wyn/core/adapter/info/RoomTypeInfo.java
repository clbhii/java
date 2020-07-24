package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * by cl at 2020/6/24 0024
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomTypeInfo implements Serializable {
    /**
     * 房型代码
     */
    @JsonProperty("RoomTypeCode")
    private String roomTypeCode;
    /**
     * 房型名称
     */
    @JsonProperty("RoomTypeName")
    private String roomTypeName;
    /**
     * 床数量
     */
    @JsonProperty("BedNumber")
    private String bedNumber;
    /**
     * 描述
     */
    @JsonProperty("Descript")
    private String descript;
    /**
     * 英文名称
     */
    @JsonProperty("EnglishName")
    private String englishName;
    /**
     * 总房间数
     */
    @JsonProperty("TotalRooms")
    private Integer totalRooms;
    /**
     * 状态1：有效，0：无效
     */
    @JsonProperty("Status")
    private Integer status;
    /**
     * 房型扩展信息
     */
    @JsonProperty("RoomExtendInfo")
    private RoomExtendInfo roomExtendInfo;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RoomExtendInfo{

        /**
         * 可入住人数
         */
        @JsonProperty("AccommodateInfo")
        private Integer accommodateInfo;
        /**
         * 房屋建筑面积
         */
        @JsonProperty("RoomAreaInfo")
        private String roomAreaInfo;
        /**
         * 房型楼层(04)
         */
        @JsonProperty("FloorInfo")
        private String floorInfo;
        /**
         * 床型
         */
        @JsonProperty("BedInfo")
        private String bedInfo;
        /**
         * 网络设施
         */
        @JsonProperty("InternetInfo")
        private String internetInfo;
        /**
         * 是否靠窗(Y)
         */
        @JsonProperty("WindowInfo")
        private String windowInfo;
        /**
         * 是否无烟房(Y)
         */
        @JsonProperty("SmookingInfo")
        private String smookingInfo;
        /**
         * 是否可加床(Y)
         */
        @JsonProperty("AddBed")
        private String addBed;
        /**
         * 床型信息1.2米/1.5米/1.8米/2.0米/其他床型
         */
        @JsonProperty("BedTypeSize")
        private String bedTypeSize;
        /**
         * 房间最大楼层
         */
        @JsonProperty("EndFloorInfo")
        private String endFloorInfo;
        /**
         * 房间最大面积
         */
        @JsonProperty("RoomAreaMax")
        private String roomAreaMax;
    }
}
