package com.cl.wyn.core.controller.vo;

import com.cl.wyn.core.adapter.info.OrderViewInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * by cl at 2020/7/2 0002
 */
@ApiModel("订单信息")
@Data
public class OrderInfoVO implements Serializable {
    /**
     * 订单编号
     */
    @ApiModelProperty("订单编号")
    private String orderNo;

    /**
     * 入住时间2017-01-05
     */
    @ApiModelProperty("入住时间2017-01-05")
    private String inDate;
    /**
     * 离店时间2017-01-07
     */
    @ApiModelProperty("离店时间2017-01-07")
    private String outDate;
    /**
     * ots平台酒店编号
     */
    @ApiModelProperty("ots平台酒店编号")
    private String hotelId;
    /**
     * 酒店名称
     */
    @ApiModelProperty("酒店名称")
    private String hotelName;

    /**
     * ots平台房型编号
     */
    @ApiModelProperty("ots平台房型编号")
    private String roomId;
    /**
     * 房型名称
     */
    @ApiModelProperty("房型名称")
    private String roomTypeName;
    /**
     * 预定间数
     */
    @ApiModelProperty("预定间数")
    private String roomNum;
    /**
     * 房间价格
     */
    @ApiModelProperty("房间价格")
    private String roomPrice;
    /**
     * 应付金额
     */
    @ApiModelProperty("应付金额")
    private String totalPrice;
    /**
     * 预付金额
     */
    @ApiModelProperty("预付金额")
    private String prePayMoney;
    /**
     * 订单总价
     */
    @ApiModelProperty("订单总价")
    private String totalOrderMoney;
    /**
     * 客户
     */
    @ApiModelProperty("客户")
    private String guestName;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;
    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String state;
    /**
     * 状态文本
     */
    @ApiModelProperty("状态文本")
    private String stateText;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String createdTime;

    public OrderInfoVO() {

    }

    public OrderInfoVO(String orderNo, String hotelId, String roomId, OrderViewInfo orderViewInfo) {
        this.orderNo = orderNo;
        this.inDate = orderViewInfo.getInDate();
        this.outDate = orderViewInfo.getOutDate();

        this.hotelId = hotelId;
        this.hotelName = orderViewInfo.getHotelName();
        this.roomId = roomId;
        this.roomTypeName = orderViewInfo.getRoomTypeName();

        this.roomNum = orderViewInfo.getRoomNum();
        this.roomPrice = orderViewInfo.getRoomPrice();
        this.totalPrice = orderViewInfo.getTotalPrice();
        this.prePayMoney = orderViewInfo.getPrePayMoney();
        this.totalOrderMoney = orderViewInfo.getTotalOrderMoney();

        this.guestName = orderViewInfo.getLinkName();
        this.mobile = orderViewInfo.getMobile();
        this.state = orderViewInfo.getState();
        this.stateText = orderViewInfo.getStateText();
        this.createdTime = orderViewInfo.getCreatedDate();
    }

}
