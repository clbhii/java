package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

/**
 * by cl at 2020/6/30 0030
 */
@AllArgsConstructor
public enum BroadnetEnum {
    ALL_YES_ALL_FREE(2, "全部房间有且免费"),
    NO(0, "否"),
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
