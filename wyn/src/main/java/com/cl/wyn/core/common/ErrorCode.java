package com.cl.wyn.core.common;

import lombok.AllArgsConstructor;

/**
 * by cl at 2020/7/2 0002
 */
@AllArgsConstructor
public enum  ErrorCode implements BaseErrorCode{

    SUCCESS("8200", "成功"),
    INTERNAL_SERVER_ERROR("8500", "服务器内部错误"),
    PARAM_EMPTY("8407", "请求参数不能为空"),
    PARAM_ERROR("8402", "请求参数非法"),
    ORDER_ERROR("7007", "下单请求失败"),
    ROOM_NUM_FAIL("9101", "房量不足"),
    CONFIRM_BOOKING_FAIL("7222", "取消预订失败"),
    ORDER_DETAIL_ERROR("7004", "订单详情失败"),
    OTHER("8500", "服务器内部错误"),
    HOTEL_PRICE_EXCEPTION("2001", "处理失败-价格计划问题"),
    HOTEL_ROOM_NOT_ENOUGH("2002", "处理失败-房量不足"),
    HOTEL_FULL_ROOM_EXCEPTION("2003", "满房"),


            ;

    /** 错误码 */
    private String resultCode;

    /** 错误描述 */
    private String resultMsg;

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
