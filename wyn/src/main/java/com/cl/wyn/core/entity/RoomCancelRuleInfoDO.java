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
 * 酒店售卖房型取消规则信息
 * </p>
 *
 * @author cl
 * @since 2020-07-15
 */
@Data
@TableName("room_cancel_rule_info")
public class RoomCancelRuleInfoDO extends BaseDO {

    @TableId
    private String id;

    /**
     * 售卖房型id
     */
    private String roomId;

    /**
     * 取消规则类型(默认1-预订后N分钟内可免费退 2-入住前xx可退款 3-有效期后xx可退款)
     */
    private Integer type;

    /**
     * 分钟(当type为1时，必填且大于等于0)
     */
    private Integer minutes;

    /**
     * 几天(2-入住日期前N天 3-有效期后N天后)
     */
    private Integer day;

    /**
     * 几时(2-入住日期前N点 3-有效期后N点后)
     */
    private Integer hour;

    /**
     * 罚金比例
     */
    private BigDecimal penaltyPercent;

}
