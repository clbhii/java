package com.cl.wyn.core.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * room_info
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
@Data
@TableName("room_info")
public class RoomInfoDO extends BaseDO {
    @TableId
    private String id;

    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 物理房型id
     */
    private String roomTypeId;

    /**
     * 售卖房型编号
     */
    private String roomCode;

    /**
     * 售卖房型名称
     */
    private String roomName;
    /**
     *售卖房型类型
     */
    private String roomType;

    /**
     * 床型
     */
    private String roomBedInfos;

    /**
     * 无线宽带默认0-免费 1-收费
     */
    private Integer broadnetType;

    /**
     * 最大入住人数
     */
    private Integer maxOccupancy;

    /**
     * 房间面积
     */
    private String areaRange;

    /**
     * 楼层
     */
    private String floorRange;

    /**
     * 是否有窗
     */
    private Integer hasWindow;

    /**
     * 加床费
     */
    private BigDecimal extraBedFee;

    /**
     * 卫浴
     */
    private Integer bathRoomType;
    /**
     * 早餐类型
     */
    private String breakfastType;
    /**
     * 至少提前预定天数
     */
    private Integer advanceDay;
    /**
     * 提前预定时间
     */
    private Date advanceTime;
    /**
     * 连续入住晚数
     */
    private Integer stayNights;
    /**
     * 是否可退
     */
    private Integer isCanCancel;
    /**
     * 1-人工确认 ,2-自动确认
     */
    private Integer orderAffirmType;

    /**
     * 房型状态
     */
    private Integer status;
    /**
     *入住人信息(默认1,1-每间只需一人填写 2-所有入住人信息填写)
     */
    private Integer personInfoType;
    /**
     *必填资料_姓名(默认1-是)
     */
    private Integer isNeedName;
    /**
     *必填资料_手机号(默认1-是)
     */
    private Integer isNeedPhone;
    /**
     *必填资料_身份证(默认0-否，1-是)
     */
    private Integer isNeedIdcard;
    /**
     *必填资料_邮箱(默认0-否，1-是)
     */
    private Integer isNeedEmail;

}
