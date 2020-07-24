package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

/**
 * by cl at 2020/6/29 0029
 */
@AllArgsConstructor
public enum FacilitiesCategoryEnum {
    PARKING("PARKING", "停车场")
    ;

    private String value;

    private String desc;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
