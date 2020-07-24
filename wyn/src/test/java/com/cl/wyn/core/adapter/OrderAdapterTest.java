package com.cl.wyn.core.adapter;

import com.cl.wyn.core.WynApplication;
import com.cl.wyn.core.adapter.IChannelAdapter;
import com.cl.wyn.core.adapter.IOrderAdapter;
import com.cl.wyn.core.adapter.IRoomAdapter;
import com.cl.wyn.core.adapter.info.*;
import com.cl.wyn.core.adapter.info.param.*;
import com.cl.wyn.core.enums.PayTypeEnum;
import com.cl.wyn.core.enums.RatePlanEnum;
import com.cl.wyn.core.util.json.JacksonBuilder;
import com.cl.wyn.core.util.json.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * by cl at 2020/6/25 0025
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WynApplication.class)
@Slf4j
public class OrderAdapterTest {
    @Autowired
    private IOrderAdapter orderAdapter;
    @Autowired
    private IChannelAdapter channelAdapter;
    @Autowired
    private IRoomAdapter roomAdapter;
    private ObjectMapper objectMapper = new JacksonBuilder().setSerializationInclusion(JsonInclude.Include.NON_NULL).build();

    @Test
    public void confirmBooking() {
        String hotelNo = "5180481";
        String roomTypeNo = "DK";
        String inDate = "2020-06-26";
        String outDate = "2020-06-27";

        RoomStatusParam param = new RoomStatusParam();
        param.setHotelNo(hotelNo);
        param.setRoomTypeNo(roomTypeNo);
        param.setInDate(inDate);
        param.setOutDate(outDate);
        param.setRatePlanList(RatePlanEnum.DisRate.getValue());
        List<RoomStatusInfo> roomStatusInfos = roomAdapter.roomStatus(param);
        String token = channelAdapter.auth();
        OrderConfirmBookingParam orderConfirmBookingParam = new OrderConfirmBookingParam();
        orderConfirmBookingParam.setToken(token);
        orderConfirmBookingParam.setHotelNo(hotelNo);
        orderConfirmBookingParam.setRoomTypeNo(roomTypeNo);
        orderConfirmBookingParam.setInDate(inDate);
        orderConfirmBookingParam.setOutDate(outDate);
        orderConfirmBookingParam.setRoomNum("2");
        BigDecimal disRate = roomStatusInfos.get(0).getRatePlanInfoList().get(0).getDisRate();
        orderConfirmBookingParam.setOrderTotalPrice(disRate.multiply(new BigDecimal(2)).toString());
        List<DailyQuotaInfo> dailyQuotaInfos = orderAdapter.confirmBooking(orderConfirmBookingParam);
        log.info(JacksonUtils.objectToJson(objectMapper, dailyQuotaInfos));
    }
    @Test
    public void booking() {
        String hotelNo = "5180481";
        String roomTypeNo = "DK";
        String inDate = "2020-06-26";
        String outDate = "2020-06-27";

        RoomStatusParam param = new RoomStatusParam();
        param.setHotelNo(hotelNo);
        param.setRoomTypeNo(roomTypeNo);
        param.setInDate(inDate);
        param.setOutDate(outDate);
        param.setRatePlanList(RatePlanEnum.DisRate.getValue());
        List<RoomStatusInfo> roomStatusInfos = roomAdapter.roomStatus(param);
        String token = channelAdapter.auth();

        OrderBookingParam orderBookingParam = new OrderBookingParam();
        orderBookingParam.setToken(token);
        orderBookingParam.setHotelNo(hotelNo);
        orderBookingParam.setRoomTypeNo(roomTypeNo);
        orderBookingParam.setInDate(inDate);
        orderBookingParam.setOutDate(outDate);
        orderBookingParam.setRoomNum("2");
        orderBookingParam.setLinkName("张三");
        orderBookingParam.setMobile("19329138001");
        orderBookingParam.setEmail("test@163.com");
        BigDecimal disRate = roomStatusInfos.get(0).getRatePlanInfoList().get(0).getDisRate();
        orderBookingParam.setOrderTotalPrice(disRate.multiply(new BigDecimal(2)).toString());
        orderBookingParam.setPayType(PayTypeEnum.A.getValue());
        orderBookingParam.setOutFlagNo("test01");
        orderBookingParam.setInNames("张三,李四");
        orderBookingParam.setOutRequestId("test000000012");
        String booking = orderAdapter.booking(orderBookingParam);
        log.info(JacksonUtils.objectToJson(objectMapper, booking));
    }
    @Test
    public void getOrderAudit() {
        String token = channelAdapter.auth();
        OrderAuditParam param = new OrderAuditParam();
        param.setToken(token);
        param.setOrderNo("C2020062614585811134DBE5180481");
        OrderAuditInfo orderAudit = orderAdapter.getOrderAudit(param);
        log.info(JacksonUtils.objectToJson(objectMapper, orderAudit));
    }
    @Test
    public void getOrderActualCheckInInfo() {
        String token = channelAdapter.auth();
        OrderActualCheckInParam param = new OrderActualCheckInParam();
        param.setToken(token);
        param.setOrderNumber("C2020062614585811134DBE5180481");
        OrderActualInfo orderActualCheckInInfo = orderAdapter.getOrderActualCheckInInfo(param);
        log.info(JacksonUtils.objectToJson(objectMapper, orderActualCheckInInfo));
    }
    @Test
    public void getOrder() {
        String token = channelAdapter.auth();
        OrderGetParam param = new OrderGetParam();
        param.setToken(token);
        param.setOrderNumber("C2020062614585811134DBE5180481");
        OrderViewInfo orderViewInfo = orderAdapter.getOrderViewInfo(param);
        log.info(JacksonUtils.objectToJson(objectMapper, orderViewInfo));
    }
    @Test
    public void cancelOrder() {
        String token = channelAdapter.auth();
        OrderCancelParam param = new OrderCancelParam();
        param.setToken(token);
        param.setOrderNumber("C2020062614585811134DBE5180481");
        orderAdapter.cancelOrder(param);
//        log.info(JacksonUtils.objectToJson(objectMapper, a));

    }
}
