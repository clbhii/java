package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

/**
 * by cl at 2020/6/28 0028
 */
@AllArgsConstructor
public enum YesNoEnum {
    YES(1, "是"),
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

    public static boolean judge(Integer value) {
        return YesNoEnum.YES.getValue().equals(value);
    }
}
