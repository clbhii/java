package com.cl.wyn.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 区域信息
 * </p>
 *
 * @author cl
 * @since 2020-06-28
 */
@Data
@TableName("district_info")
public class DistrictInfoDO extends BaseDO {
    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级id
     */
    private String parentId;

    /**
     * 字典父节点code
     */
    private String parentCode;

    /**
     * 维也纳是否同步 0：否，1：是,默认为0
     */
    private Integer isWyn88Synchronized;

}
