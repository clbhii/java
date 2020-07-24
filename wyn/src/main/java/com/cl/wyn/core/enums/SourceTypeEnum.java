package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

/**
 * by cl at 2020/6/28 0028
 */
@AllArgsConstructor
public enum SourceTypeEnum {
    OTA(1, "OTA对接")
    ;

    private Integer value;

    private String desc;

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
