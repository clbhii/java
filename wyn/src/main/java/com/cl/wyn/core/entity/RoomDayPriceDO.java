package com.cl.wyn.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 售卖房型日态价格信息
 * </p>
 *
 * @author cl
 * @since 2020-07-01
 */
@Data
@TableName("room_day_price")
public class RoomDayPriceDO extends BaseDO {

    @TableId
    private String id;

    /**
     * 售卖房型id
     */
    private String roomId;

    /**
     * 早餐数量
     */
    private Integer breakfastNum;

    /**
     * 午餐数量
     */
    private Integer lunchNum;

    /**
     * 晚餐数量
     */
    private Integer dinnerNum;

    /**
     * 是否半餐
     */
    private String isOptionalMeal;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 已定房量
     */
    private Integer bookedQuantity;

    /**
     * 采购价
     */
    private BigDecimal costPrice;

    /**
     * 销售指导价
     */
    private BigDecimal salePrice;

    /**
     * 分销价
     */
    private BigDecimal distributorPrice;

    /**
     * 加价率
     */
    private BigDecimal increaseRate;

    /**
     * 是否启用加价率
     */
    private Integer isEnabled;

    /**
     * 日期
     */
    private Date date;

    /**
     * 星期
     */
    private Integer week;

    /**
     * 房型是否售卖
     */
    private Integer isSelling;




}
