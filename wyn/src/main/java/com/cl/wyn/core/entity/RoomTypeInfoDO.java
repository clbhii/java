package com.cl.wyn.core.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 物理房型信息
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
@Data
@TableName("room_type_info")
public class RoomTypeInfoDO extends BaseDO {


    @TableId
    private String id;
    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 物理房型编号
     */
    private String roomTypeCode;

    /**
     * 物理房型名称
     */
    private String roomTypeName;

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
     * 封面图
     */
    private String cover;

    /**
     * 最低价
     */
    private BigDecimal lowPrice;

    /**
     * 0下架，1上架
     */
    private Integer isSelling;

}
