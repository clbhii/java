package com.cl.wyn.core.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 返回代码1：成功 0：失败 -1：参数错误 -2：系统异常 -3：凭证错误
 */

@AllArgsConstructor
public enum WynErrorCode {
    SUCCESS("1", "成功", ErrorCode.SUCCESS),
    FAIL("0", "失败", ErrorCode.INTERNAL_SERVER_ERROR),
    PARAM_ERROR("-1","参数错误", ErrorCode.PARAM_ERROR),
    SYSTEM_ERROR("-2","系统异常", ErrorCode.INTERNAL_SERVER_ERROR),
    TOKEN_ERROR("-3","凭证错误", ErrorCode.INTERNAL_SERVER_ERROR),
    ORDER_FAIL_ROOM_PRICE("-101","提交订单失败：房价已发生变化", ErrorCode.HOTEL_PRICE_EXCEPTION),
    ORDER_FAIL_ROOM_NUM("-111","提交订单失败：没有可预订的活动房间数量或已经预订完", ErrorCode.HOTEL_FULL_ROOM_EXCEPTION),
    ROOM_NUM_FAIL("-115","房量不足", ErrorCode.HOTEL_ROOM_NOT_ENOUGH),
    OTHER("未知","未知", ErrorCode.OTHER),


    ;
    /** 错误码 */
    private String resultCode;

    /** 错误描述 */
    private String resultMsg;

    private ErrorCode errorCode;

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public static WynErrorCode getWynErrorCode(String code) {
        for(WynErrorCode wynErrorCode : WynErrorCode.values()){
            if(wynErrorCode.getResultCode().equals(code)){
                return wynErrorCode;
            }
        }
        return WynErrorCode.OTHER;
    }


}
