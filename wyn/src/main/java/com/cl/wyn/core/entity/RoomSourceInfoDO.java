package com.cl.wyn.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 酒店售卖房型来源信息
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
@Data
@TableName("room_source_info")
public class RoomSourceInfoDO extends BaseDO {

    @TableId
    private String id;

    /**
     * 供应商id
     */
    private String supplierId;

    /**
     * 供应商酒店id
     */
    private String supplierHotelId;

    /**
     * 供应商物理房型id
     */
    private String supplierRoomTypeId;

    /**
     * 供应商售卖房型id
     */
    private String supplierRoomId;

    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 物理房型id
     */
    private String roomTypeId;

    /**
     * 售卖房型id
     */
    private String roomId;

    /**
     * 可定检查流水号
     */
    private String outBookingCode;

    /**
     * 价格计划ID
     */
    private String outRatePlanId;

    /**
     * 来源类型
     */
    private Integer source;




}
