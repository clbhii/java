package com.cl.wyn.core.controller.vo;

import com.cl.wyn.core.adapter.info.DailyQuotaInfo;
import com.cl.wyn.core.entity.RoomDayPriceDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * by cl at 2020/7/2 0002
 */
@ApiModel("试单结果")
@Data
public class OrderConfirmBookingVO implements Serializable {
    /**
     * 日期
     */
    @ApiModelProperty("日期")
    private String date;

    /**
     * 结算价
     */
    @ApiModelProperty("结算价")
    private String disrate;
    /**
     * 下单时的价格
     */
    @ApiModelProperty("下单时的价格")
    private String bookPrice;
    /**
     * 底价
     */
    @ApiModelProperty("底价")
    private String price;
    /**
     * 酒店id
     */
    @ApiModelProperty("酒店id")
    private String hotelId;
    /**
     * 售卖房型
     */
    @ApiModelProperty("售卖房型")
    private String roomId;
    /**
     * 可预订房量
     */
    @ApiModelProperty("可预订房量")
    private String canBookRoomNum;

    public OrderConfirmBookingVO() {

    }
    public OrderConfirmBookingVO(String hotelId, String roomId, DailyQuotaInfo dailyQuotaInfo, RoomDayPriceDO roomDayPriceDO){
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.date = dailyQuotaInfo.getDate();
        this.disrate = dailyQuotaInfo.getDisRate().toString();
        if(roomDayPriceDO != null) {
            this.bookPrice = roomDayPriceDO.getDistributorPrice().toString();
            this.price = roomDayPriceDO.getSalePrice().toString();
        }
        this.canBookRoomNum = dailyQuotaInfo.getQuota().toString();

    }

}
