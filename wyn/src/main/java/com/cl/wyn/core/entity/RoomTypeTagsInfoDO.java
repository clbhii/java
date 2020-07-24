package com.cl.wyn.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 物理房型标签信息
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
@Data
@TableName("room_type_tags_info")
public class RoomTypeTagsInfoDO extends BaseDO {

    @TableId
    private String id;

    /**
     * 物理房型id
     */
    private String roomTypeId;

    /**
     * 标签类型
     */
    private String type;

    /**
     * 标签编码
     */
    private String code;

}
