package com.cl.wyn.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 物理房型图片信息
 * </p>
 *
 * @author cl
 * @since 2020-06-30
 */
@Data
@TableName("room_type_pictures_info")
public class RoomTypePicturesInfoDO extends BaseDO {

    @TableId
    private String id;

    /**
     * 物理房型id
     */
    private String roomTypeId;

    /**
     * 图片url
     */
    private String url;

    /**
     * 图片类型
     */
    private Integer type;




}
