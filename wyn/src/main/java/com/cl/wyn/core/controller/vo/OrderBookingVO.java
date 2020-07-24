package com.cl.wyn.core.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * by cl at 2020/7/3 0003
 */
@ApiModel("预订结果")
@Data
public class OrderBookingVO implements Serializable {

    /**
     * 外部订单编号
     */
    @ApiModelProperty("外部订单编号")
    private String outOrderNo;

    public OrderBookingVO() {
    }

    public OrderBookingVO(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }
}
