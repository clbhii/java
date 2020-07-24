package com.cl.wyn.core.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 酒店外部设施信息
 * </p>
 *
 * @author cl
 * @since 2020-06-29
 */
@Data
@TableName("hotel_external_facilities_info")
public class HotelExternalFacilitiesInfoDO extends  BaseDO{

    @TableId
    private String id;

    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类别
     */
    private String categoryName;

    /**
     * 是否提供
     */
    private Integer isProvide;



}
