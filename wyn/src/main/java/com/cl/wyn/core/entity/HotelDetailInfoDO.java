package com.cl.wyn.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 酒店详细信息
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
@Data
@TableName("hotel_detail_info")
public class HotelDetailInfoDO extends BaseDO {

    @TableId
    private String id;

    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 简介
     */
    private String description;



}
