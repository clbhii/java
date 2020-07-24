package com.cl.wyn.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;

/**
 * <p>
 * 同步记录表
 * </p>
 *
 * @author cl
 * @since 2020-07-03
 */
@Data
@TableName("sync_record")
public class SyncRecordDO implements Serializable {

    private static final long serialVersionUID=1L;
    /**
     * 开始时间
     */
    private Date dateStart;

    /**
     * 结束时间
     */
    private Date dateEnd;

    /**
     * 结果(SUCCESS, FAIL)
     */
    private String result;

    /**
     * 备注
     */
    private String remark;


}
