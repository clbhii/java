package com.cl.wyn.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 请求记录表
 * </p>
 *
 * @author cl
 * @since 2020-07-02
 */
@Data
@TableName("request_record")
public class RequestRecordDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 请求地址
     */
    private String path;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 结果
     */
    private String result;

    /**
     * 耗时
     */
    private Long costTime;

    /**
     * 请求时间
     */
    private Date requestTime;


}
