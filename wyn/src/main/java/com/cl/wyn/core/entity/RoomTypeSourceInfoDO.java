package com.cl.wyn.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 酒店物理房型来源信息
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
@Data
@TableName("room_type_source_info")
public class RoomTypeSourceInfoDO extends BaseDO {

    @TableId
    private String id;

    /**
     * 来源id
     */
    private String sourceId;

    /**
     * 来源酒店id
     */
    private String sourceHotelId;

    /**
     * 供应商物理房型id
     */
    private String sourceRoomTypeId;

    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 物理房型id
     */
    private String roomTypeId;

    /**
     * 来源类型
     */
    private Integer sourceType;

    /**
     * 来源子类型
     */
    private Integer subSourceType;



}
