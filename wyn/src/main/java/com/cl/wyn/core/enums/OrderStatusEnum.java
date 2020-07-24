package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

/**
 * by cl at 2020/6/26 0026
 */
@AllArgsConstructor
public enum OrderStatusEnum {
    BOOK_SUCCESS("6", "预定成功"),
    NOT_ARRIVE("9", "未到店"),
    CHECKED_IN("8", "已入住"),
    CHECKED_OUT("10", "已离店"),
    CANCEL("4", "取消"),
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
