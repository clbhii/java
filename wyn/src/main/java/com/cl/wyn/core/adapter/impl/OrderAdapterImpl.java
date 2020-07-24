package com.cl.wyn.core.adapter.impl;

import com.cl.wyn.core.adapter.IOrderAdapter;
import com.cl.wyn.core.adapter.info.*;
import com.cl.wyn.core.adapter.info.param.*;
import com.cl.wyn.core.common.BizException;
import com.cl.wyn.core.common.ErrorCode;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * by cl at 2020/6/25 0025
 */
@Component
public class OrderAdapterImpl extends AbsAdapter implements IOrderAdapter {
    @Value("${wyn.api.order.confirmBookingPath}")
    private String confirmBookingPath;
    @Value("${wyn.api.order.bookingPath}")
    private String bookingPath;
    @Value("${wyn.api.order.getOrderAuditPath}")
    private String getOrderAuditPath;
    @Value("${wyn.api.order.getOrderActualCheckInPath}")
    private String getOrderActualCheckInPath;
    @Value("${wyn.api.order.getOrderPath}")
    private String getOrderPath;
    @Value("${wyn.api.order.cancelOrderPath}")
    private String cancelOrderPath;

    @Override
    public List<DailyQuotaInfo> confirmBooking(OrderConfirmBookingParam param) {
        return post(confirmBookingPath, param, new TypeReference<BaseResult<List<DailyQuotaInfo>>>() {});
    }

    @Override
    public String booking(OrderBookingParam param) {
        return post(bookingPath, param, new TypeReference<BaseResult<String>>() {});
    }

    @Override
    public OrderAuditInfo getOrderAudit(OrderAuditParam param) {
        return post(getOrderAuditPath, param, new TypeReference<BaseResult<OrderAuditInfo>>() {});
    }

    @Override
    public OrderActualInfo getOrderActualCheckInInfo(OrderActualCheckInParam param) {
        return post(getOrderActualCheckInPath, param, new TypeReference<BaseResult<List<OrderActualInfo>>>() {});
    }

    @Override
    public OrderViewInfo getOrderViewInfo(OrderGetParam param) {
        try{
            return post(getOrderPath, param, new TypeReference<BaseResult<OrderViewInfo>>() {});
        }catch (BizException e) {
            if(ErrorCode.OTHER.getResultCode().equals(e.getErrorCode())){
                throw new BizException(ErrorCode.ORDER_DETAIL_ERROR, e.getMessage(), e);
            }else {
                throw e;
            }
        }

    }

    @Override
    public void cancelOrder(OrderCancelParam param) {
        try{
            post(cancelOrderPath, param, new TypeReference<BaseResult<String>>() {});
        }catch (BizException e) {
            if(ErrorCode.OTHER.getResultCode().equals(e.getErrorCode())){
                throw new BizException(ErrorCode.CONFIRM_BOOKING_FAIL, e.getMessage(), e);
            }else {
                throw e;
            }

        }
    }
}
