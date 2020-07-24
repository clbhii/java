package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

/**
 * by cl at 2020/6/25 0025
 */
@AllArgsConstructor
public enum RatePlanEnum {
    DisRate("DisRate", "渠道协议价")
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
