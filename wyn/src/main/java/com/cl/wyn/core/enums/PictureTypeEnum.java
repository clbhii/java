package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

/**
 * by cl at 2020/6/30 0030
 */
@AllArgsConstructor
public enum PictureTypeEnum {
    OTHER(0, "其他"),
    FACADE(1, "外观"),
    ROOM(2, "房间"),
    PUBLIC(3, "公共区域"),

    ;
    private Integer value;

    private String desc;

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


    public static PictureTypeEnum getPictureTypeEnum(String str) {
        if (str.endsWith("房") || str.endsWith("间")) {
            return PictureTypeEnum.ROOM;
        } else if (str.contains("大堂") || str.contains("外观") || str.contains("夜景") || str.contains("区")
                || str.contains("厅")) {
            return PictureTypeEnum.PUBLIC;
        } else if (str.contains("门头") || str.contains("门楼") || str.contains("入口") || str.contains("侧面")
                || str.contains("全景")) {
            return PictureTypeEnum.FACADE;
        }
        return PictureTypeEnum.OTHER;
    }


}
