package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PayTypeEnum {
    A("A", "到店现付"),
    B("B", "预付"),
    C("C", "离店后付"),
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
