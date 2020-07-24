package com.cl.wyn.core.adapter;

import com.cl.wyn.core.adapter.info.DailyQuotaInfo;
import com.cl.wyn.core.adapter.info.OrderActualInfo;
import com.cl.wyn.core.adapter.info.OrderAuditInfo;
import com.cl.wyn.core.adapter.info.OrderViewInfo;
import com.cl.wyn.core.adapter.info.param.*;

import java.util.List;

/**
 * by cl at 2020/6/25 0025
 */
public interface IOrderAdapter {

    /**
     * 试单接口
     * @param param
     * @return
     */
    List<DailyQuotaInfo> confirmBooking(OrderConfirmBookingParam param);

    /**
     * 房间预定
     * @param param
     * @return
     */
    String booking(OrderBookingParam param);

    /**
     * 获取订单审核信息
     * @param param
     * @return
     */
    OrderAuditInfo getOrderAudit(OrderAuditParam param);

    /**
     * 获取订单实际入住情况
     * @param param
     * @return
     */
    OrderActualInfo getOrderActualCheckInInfo(OrderActualCheckInParam param);

    /**
     * 根据订单号获取订单信息
     * @param param
     * @return
     */
    OrderViewInfo getOrderViewInfo(OrderGetParam param);

    /**
     * 取消订单
     * @param param
     * @return
     */
    void cancelOrder(OrderCancelParam param);
}
