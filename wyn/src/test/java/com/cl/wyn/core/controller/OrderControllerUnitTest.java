package com.cl.wyn.core.controller;

import com.cl.wyn.core.adapter.IChannelAdapter;
import com.cl.wyn.core.adapter.IOrderAdapter;
import com.cl.wyn.core.adapter.info.param.OrderCancelParam;
import com.cl.wyn.core.common.BizException;
import com.cl.wyn.core.common.ErrorCode;
import com.cl.wyn.core.controller.param.OrderCancelApiParam;
import com.cl.wyn.core.service.IRoomDayPriceService;
import com.cl.wyn.core.service.IRoomSourceInfoService;
import com.cl.wyn.core.service.ISynchronousDataService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * by cl at 2020/7/8 0008
 */
@RunWith(JMockit.class)
public class OrderControllerUnitTest {

    @Tested
    private OrderController orderController;

    @Injectable
    private IChannelAdapter channelAdapter;
    @Injectable
    private IOrderAdapter orderAdapter;
    @Injectable
    private IRoomSourceInfoService roomSourceInfoService;
    @Injectable
    private IRoomDayPriceService roomDayPriceService;
    @Injectable
    private ISynchronousDataService synchronousDataService;
    @Test
    public void cancelOrder() {
        new Expectations() {
            {
                channelAdapter.auth();
                result = "token01";
                orderAdapter.cancelOrder((OrderCancelParam)any);
                result = new BizException(ErrorCode.CONFIRM_BOOKING_FAIL);
            }
        };
        //参数为空
        OrderCancelApiParam orderCancelApiParam = new OrderCancelApiParam();
        orderCancelApiParam.setOrderNo(null);
        try{
            orderController.cancelOrder(orderCancelApiParam);
        }catch (BizException e) {
            assert e.getErrorCode().equals(ErrorCode.PARAM_EMPTY.getResultCode());
        }
        //取消预约失败
        orderCancelApiParam = new OrderCancelApiParam();
        orderCancelApiParam.setOrderNo("111");
        try{
            orderController.cancelOrder(orderCancelApiParam);
        }catch (BizException e) {
            assert e.getErrorCode().equals(ErrorCode.CONFIRM_BOOKING_FAIL.getResultCode());
        }
        //正常
        new Expectations() {
            {
                orderAdapter.cancelOrder((OrderCancelParam)any);

            }
        };
        orderController.cancelOrder(orderCancelApiParam);
    }
}
