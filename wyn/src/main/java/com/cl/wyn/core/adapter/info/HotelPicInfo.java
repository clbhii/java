package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * by cl at 2020/6/24 0024
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelPicInfo implements Serializable {

    /**
     * 图片名称
     */
    @JsonProperty("Title")
    private String title;
    /**
     * 图片描述
     */
    @JsonProperty("Summary")
    private String summary;
    /**
     * 小图地址
     */
    @JsonProperty("SmallImageUrl")
    private String smallImageUrl;
    /**
     * 大图地址
     */
    @JsonProperty("BigImageUrl")
    private String bigImageUrl;
    /**
     * 图片分类
     */
    @JsonProperty("Category")
    private String category;
}
