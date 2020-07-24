package com.cl.wyn.core.controller.param;

import com.cl.wyn.core.common.Assert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.apache.http.util.Asserts;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * by cl at 2020/7/2 0002
 */
@ApiModel("试单亲戚参数")
@Data
public class OrderConfirmBookingApiParam implements Serializable {

    /**
     * ots平台酒店编号
     */
    @ApiModelProperty("ots平台酒店编号")
    @Pattern(regexp = "^[0-9]+$", message = "hotelId只能是数字，不能是中文或是特殊字符")
    @NotBlank(message = "hotelId不能为空")
    private String hotelId;

    /**
     * ots平台房型编号
     */
    @ApiModelProperty("ots平台房型编号")
    @Pattern(regexp = "^[0-9]+$", message = "hotelId只能是数字，不能是中文或是特殊字符")
    @NotBlank(message = "roomId不能为空")
    private String roomId;
    /**
     * 入住时间2017-01-05
     */
    @ApiModelProperty("入住时间2017-01-05")
    @NotBlank(message = "checkInTime不能为空")
    private String checkInTime;
    /**
     * 离店时间2017-01-07
     */
    @ApiModelProperty("离店时间2017-01-07")
    @NotBlank(message = "checkOutTime不能为空")
    private String checkOutTime;
    /**
     * 预定间数
     */
    @ApiModelProperty("预定间数")
    @NotBlank(message = "roomNum不能为空")
    @Pattern(regexp = "^[1-9][0-9]*$", message = "roomNum只能是正整数，不能是负数或是0")
    private String roomNum;
    /**
     *入住总人数
     */
    @ApiModelProperty("入住总人数")
    @NotBlank(message = "guestCount不能为空")
    @Pattern(regexp = "^[1-9][0-9]*$", message = "guestCount只能是正整数，不能是负数或是0")
    private String guestCount;
    /**
     * 订单总价
     */
    @ApiModelProperty("订单总价")
    @NotBlank(message = "totalPrice不能为空")
    @Pattern(regexp = "^[1-9][0-9]*$", message = "totalPrice只能是正整数，不能是负数或是0")
    private String totalPrice;

    public void validate() {
        Assert.notEmpty("hotelId", hotelId);
        Assert.notEmpty("roomId", roomId);
        Assert.notEmpty("checkInTime", checkInTime);
        Assert.notEmpty("checkOutTime", checkOutTime);
        Assert.notEmpty("roomNum", roomNum);
        Assert.notEmpty("guestCount", guestCount);
        Assert.notEmpty("totalPrice", totalPrice);
    }

}
