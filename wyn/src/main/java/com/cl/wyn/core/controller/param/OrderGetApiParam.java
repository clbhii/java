package com.cl.wyn.core.controller.param;

import com.cl.wyn.core.common.Assert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * by cl at 2020/7/8 0008
 */
@ApiModel("获取订单参数")
@Data
public class OrderGetApiParam implements Serializable {

    /**
     * 订单编号
     */
    @ApiModelProperty("订单编号")
    private String orderNo;

    public void validate() {
        Assert.notEmpty("orderNo", orderNo);
    }
}
