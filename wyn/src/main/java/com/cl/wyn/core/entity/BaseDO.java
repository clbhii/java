package com.cl.wyn.core.entity;

import com.cl.wyn.core.common.Contants;
import com.cl.wyn.core.enums.YesNoEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * by cl at 2020/6/28 0028
 */
@Data
public class BaseDO implements Serializable {

    /**
     * 创建人
     */
    private String creatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String modifiedId;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 可见 0：不可见，1：可见
     */
    private Integer isVisible;

    /**
     *
     * 删除 0：未删除，1：删除
     */
    private Integer isDeleted;

    public void createDeFault() {
        this.creatorId = Contants.SYSTEM;
        this.createTime = new Date();
        this.isVisible = YesNoEnum.YES.getValue();
        this.isDeleted = YesNoEnum.NO.getValue();
    }

    public void updateDefault() {
        this.modifiedId = Contants.SYSTEM;
        this.modifiedTime = new Date();
    }

}
