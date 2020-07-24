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
 * 酒店信息
 * </p>
 *
 * @author cl
 * @since 2020-06-28
 */
@Data
@TableName("hotel_info")
public class HotelInfoDO extends BaseDO {

    private static final long serialVersionUID=1L;
    @TableId
    private String id;
    /**
     * 酒店中文名
     */
    private String hotelName;

    /**
     * 酒店编码
     */
    private String hotelCode;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 星级
     */
    private Integer starRating;

    /**
     * 固定电话
     */
    private String tel;

    /**
     * 酒店品牌
     */
    private String brandName;

    /**
     * 酒店当日最低价
     */
    private BigDecimal lowPrice;

    /**
     * 封面图
     */
    private String cover;

    /**
     * 开业时间
     */
    private String openYear;

    /**
     * 房间数量
     */
    private Integer roomQuantity;

    /**
     * 实际已定房量
     */
    private Integer realBookedRoomQuantity;

    /**
     * 虚拟已定房量
     */
    private Integer virtualBookedRoomQuantity;

    /**
     * 是否有外部设施
     */
    private Integer isHasExternalFacilities;
}
