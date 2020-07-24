package com.cl.wyn.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 酒店标签信息
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
@Data
@TableName("hotel_tags_info")
public class HotelTagsInfoDO extends BaseDO{

    @TableId
    private String id;

    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 标签类型
     */
    private String type;

    /**
     * 标签编码
     */
    private String code;



}
