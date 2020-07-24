package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SubSourceTypeEnum {
    WYN(2, "维也纳")
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
