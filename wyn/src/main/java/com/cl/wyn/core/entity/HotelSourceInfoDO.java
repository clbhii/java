package com.cl.wyn.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 酒店来源信息
 * </p>
 *
 * @author cl
 * @since 2020-06-28
 */
@Data
@TableName("hotel_source_info")
public class HotelSourceInfoDO extends BaseDO {

    private static final long serialVersionUID=1L;
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
     * 酒店id
     */
    private String hotelId;

    /**
     * 来源类型
     */
    private Integer sourceType;

    /**
     * 来源子类型
     */
    private Integer subSourceType;


}
