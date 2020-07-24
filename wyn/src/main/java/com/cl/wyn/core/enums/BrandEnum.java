package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

/**
 * by cl at 2020/6/29 0029
 */
@AllArgsConstructor
public enum BrandEnum {
    BRAND_1("1", "维也纳3好酒店"),
    BRAND_2("2", "维也纳酒店"),
    BRAND_3("3", "维也纳国际酒店"),
    BRAND_4("4", "维纳斯皇家酒店"),
    BRAND_5("5", "维纳斯度假村酒店"),
    BRAND_6("6", "维也纳智好酒店"),
    BRAND_7("7", "维也纳公寓"),
    BRAND_8("8", "维也纳大健康酒店"),
    BRAND_9("9", "维也纳好眠国际酒店"),
    BRAND_10("10", "维纳斯国际酒店"),
    BRAND_11("11", "诚悦"),
    BRAND_12("12", "凯里亚德酒店"),
    BRAND_15("15", "郁锦香酒店"),
    BRAND_16("16", "丽柏酒店"),
    BRAND_17("17", "丽怡酒店"),
    BRAND_18("18", "丽亭酒店"),
    BRAND_19("19", "欧暇·地中海酒店"),
    ;

    private String value;

    private String desc;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


//    public static BrandEnum getBrandEnum(String value) {
//        for(BrandEnum brandEnum : BrandEnum.values()){
//            if(brandEnum.getValue().equals(value)){
//                return brandEnum;
//            }
//        }
//        return null;
//    }

    public static String getDesc(String value) {
        for(BrandEnum brandEnum : BrandEnum.values()){
            if(brandEnum.getValue().equals(value)){
                return brandEnum.getDesc();
            }
        }
        return null;
    }
}
