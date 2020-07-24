package com.cl.wyn.core.adapter.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * by cl at 2020/6/27 0027
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderViewInfo implements Serializable {
    /**
     * 订单编号
     */
    @JsonProperty("OrderNumber")
    private String orderNumber;
    /**
     * 入住时间
     */
    @JsonProperty("InDate")
    private String inDate;
    /**
     * 离店时间
     */
    @JsonProperty("OutDate")
    private String outDate;
    /**
     * 酒店code
     */
    @JsonProperty("HotelCode")
    private String hotelCode;
    /**
     * 酒店编号
     */
    @JsonProperty("HotelNo")
    private String hotelNo;
    /**
     * 酒店名称
     */
    @JsonProperty("HotelName")
    private String hotelName;
    /**
     * 房型编号
     */
    @JsonProperty("RoomTypeCode")
    private String roomTypeCode;
    /**
     * 房型名称
     */
    @JsonProperty("RoomTypeName")
    private String roomTypeName;
    /**
     * 房间数据
     */
    @JsonProperty("RoomNum")
    private String roomNum;
    /**
     * 房费
     */
    @JsonProperty("RoomPrice")
    private String roomPrice;
    /**
     * 应付金额
     */
    @JsonProperty("TotalPrice")
    private String totalPrice;
    /**
     * 预付
     */
    @JsonProperty("PrePayMoney")
    private String prePayMoney;
    /**
     * 订单总价
     */
    @JsonProperty("TotalOrderMoney")
    private String totalOrderMoney;
    /**
     * 联系人
     */
    @JsonProperty("LinkName")
    private String linkName;
    /**
     * 手机号
     */
    @JsonProperty("Mobile")
    private String mobile;
    /**
     * 订单状态2:正在处理4:订单取消6:预订成功8:已入住9:客人未到10:已离店
     */
    @JsonProperty("State")
    private String state;
    /**
     * 订单状态说明
     */
    @JsonProperty("StateText")
    private String stateText;
    /**
     * 是否支付（Y:是M:否）
     */
    @JsonProperty("Payment")
    private String payment;
    /**
     * 订单创建时间
     */
    @JsonProperty("CreatedDate")
    private String createdDate;
    /**
     * 订单提交类型
     */
    @JsonProperty("Order_sub_type")
    private String orderSubType;
    /**
     * 预订单编号
     */
    @JsonProperty("ResvID")
    private String resvId;
    /**
     * 是否已排房
     */
    @JsonProperty("Assign")
    private String assign;
    /**
     * 备注
     */
    @JsonProperty("Remark")
    private String remark;

}
