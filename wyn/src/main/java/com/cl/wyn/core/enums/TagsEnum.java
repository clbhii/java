package com.cl.wyn.core.enums;

import lombok.AllArgsConstructor;

/**
 * by cl at 2020/6/29 0029
 */
@AllArgsConstructor
public enum TagsEnum {
    wifi("1305", "客房WIFI", TagsTypeEnum.roomfacilities),
    musicExperience("358", "音乐体验", TagsTypeEnum.features),
    airportShuttle("853", "接机服务", TagsTypeEnum.hotelfacilities),
    meetingRoom("804", "会议厅(宴会)", TagsTypeEnum.hotelfacilities),
    businessCenter("805", "商务中心", TagsTypeEnum.hotelfacilities),
    breakfast("1009", "含早", TagsTypeEnum.hotelfacilities),
    ktv("812", "KTV", TagsTypeEnum.hotelfacilities),
    swimmingPool("832", "室外游泳池", TagsTypeEnum.hotelfacilities),
    gymnasium("826", "健身房", TagsTypeEnum.hotelfacilities),


    ;

    private String value;

    private String desc;

    private TagsTypeEnum tagsTypeEnum;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public TagsTypeEnum getTagsTypeEnum() {
        return tagsTypeEnum;
    }

}
