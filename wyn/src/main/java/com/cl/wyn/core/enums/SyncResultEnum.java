package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

/**
 *
 */
@AllArgsConstructor
public enum SyncResultEnum {
    SUCCESS("SUCCESS", "成功"),
    FAIL("FAIL", "失败"),
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
