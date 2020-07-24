package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

/**
 * by cl at 2020/6/29 0029
 */
@AllArgsConstructor
public enum TagsTypeEnum {
    themes("themes", "酒店主题"),
    features("features", "酒店特色"),
    category("category", "酒店分类"),
    business_district("business_district", "热门商圈"),
    hotelfacilities("facilities", "酒店设施"),
    roomfacilities("facilities", "房型设施"),
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
