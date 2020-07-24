package com.cl.wyn.core.adapter.info.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * by cl at 2020/6/26 0026
 */
@Data
public class OrderBookingParam extends BaseParam{

    /**
     * 渠道凭证
     */
    private String token;
    /**
     * 酒店编号
     */
    @JsonProperty("hotelno")
    private String hotelNo;
    /**
     * 酒店编号
     */
    @JsonProperty("roomtypeno")
    private String roomTypeNo;
    /**
     * 入住时间
     */
    @JsonProperty("indate")
    private String inDate;
    /**
     * 离店时间
     */
    @JsonProperty("outdate")
    private String outDate;
    /**
     * 预定房间数
     */
    @JsonProperty("roomnum")
    private String roomNum;
    /**
     * 到达时间
     */
    @JsonProperty("arrivetime")
    private String arriveTime;
    /**
     * 联系人
     */
    @JsonProperty("linkname")
    private String linkName;
    /**
     * 手机号
     */
    @JsonProperty("mobile")
    private String mobile;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 订单总价
     */
    @JsonProperty("ordertotalprice")
    private String orderTotalPrice;
    /**
     * 订单备注
     */
    private String remark;
    /**
     * 支付类型A：到店现付B：预付（必填）C：离店后付（诚信住）
     */
    @JsonProperty("paytype")
    private String payType;
    /**
     * 外部渠道订单编号
     */
    @JsonProperty("outflagno")
    private String outFlagNo;
    /**
     * 入住人，多个用,隔开
     */
    @JsonProperty("innames")
    private String inNames;
    /**
     * 外部渠道订单标识
     */
    @JsonProperty("outrequestid")
    private String outRequestId;
    /**
     * 价格计划代码YbRate：贵宾卡价格ZsRate：钻石卡价格ZzRate：至尊钻石卡价格SsRate：绅士卡价格
     */
    @JsonProperty("rateplancode")
    private String ratePlanCode;
    /**
     * 协议编号
     */
    @JsonProperty("agrno")
    private String agrNo;

}
