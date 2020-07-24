package com.cl.wyn.core.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cl.wyn.core.adapter.IChannelAdapter;
import com.cl.wyn.core.adapter.IOrderAdapter;
import com.cl.wyn.core.adapter.IRoomAdapter;
import com.cl.wyn.core.adapter.info.DailyQuotaInfo;
import com.cl.wyn.core.adapter.info.OrderViewInfo;
import com.cl.wyn.core.adapter.info.RoomStatusInfo;
import com.cl.wyn.core.adapter.info.param.*;
import com.cl.wyn.core.common.*;
import com.cl.wyn.core.controller.param.OrderBookingApiParam;
import com.cl.wyn.core.controller.param.OrderCancelApiParam;
import com.cl.wyn.core.controller.param.OrderConfirmBookingApiParam;
import com.cl.wyn.core.controller.param.OrderGetApiParam;
import com.cl.wyn.core.controller.vo.OrderBookingVO;
import com.cl.wyn.core.controller.vo.OrderConfirmBookingVO;
import com.cl.wyn.core.controller.vo.OrderInfoVO;
import com.cl.wyn.core.entity.RoomDayPriceDO;
import com.cl.wyn.core.entity.RoomSourceInfoDO;
import com.cl.wyn.core.enums.PayTypeEnum;
import com.cl.wyn.core.enums.RatePlanEnum;
import com.cl.wyn.core.service.IRoomDayPriceService;
import com.cl.wyn.core.service.IRoomSourceInfoService;
import com.cl.wyn.core.service.ISynchronousDataService;
import com.cl.wyn.core.util.common.DateUtil;
import com.cl.wyn.core.util.common.ThreadPoolUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * by cl at 2020/7/2 0002
 */
@RequestMapping("/order")
@RestController
@Api(tags = "订单模块")
public class OrderController {
    @Autowired
    private IChannelAdapter channelAdapter;
    @Autowired
    private IOrderAdapter orderAdapter;
    @Autowired
    private IRoomAdapter roomAdapter;
    @Autowired
    private IRoomSourceInfoService roomSourceInfoService;
    @Autowired
    private IRoomDayPriceService roomDayPriceService;
    @Autowired
    private ISynchronousDataService synchronousDataService;

    /**
     * 试单
     * @param orderConfirmBookingApiParam
     * @return
     */
    @ApiOperation(value = "试单", httpMethod = "POST")
    @RequestMapping(value = "/confirmBooking", method = RequestMethod.POST)
    public Result<OrderConfirmBookingVO> confirmBooking(@RequestBody  @Valid OrderConfirmBookingApiParam orderConfirmBookingApiParam, BindingResult bindingResult) {
        validate(bindingResult);
        RoomSourceInfoDO roomSourceInfoDO = roomSourceInfoService.getOne(new QueryWrapper<RoomSourceInfoDO>().lambda().eq(RoomSourceInfoDO::getRoomId, orderConfirmBookingApiParam.getRoomId()).and(i->i.eq(RoomSourceInfoDO::getHotelId, orderConfirmBookingApiParam.getHotelId())));
        if(roomSourceInfoDO == null) {
            throw new BizException(ErrorCode.PARAM_ERROR, "获取不到对应的数据，输入不存在的hotelId或是roomId");
        }
        //判定是否更新日态信息
        synchronousDataService.synRoomDayPrice(roomSourceInfoDO, orderConfirmBookingApiParam.getCheckInTime(), orderConfirmBookingApiParam.getCheckOutTime());
        //试单
        OrderConfirmBookingParam orderConfirmBookingParam = new OrderConfirmBookingParam();
        orderConfirmBookingParam.setToken(channelAdapter.auth());
        orderConfirmBookingParam.setHotelNo(roomSourceInfoDO.getSupplierHotelId());
        orderConfirmBookingParam.setRoomTypeNo(roomSourceInfoDO.getSupplierRoomId());
        orderConfirmBookingParam.setInDate(orderConfirmBookingApiParam.getCheckInTime());
        orderConfirmBookingParam.setOutDate(orderConfirmBookingApiParam.getCheckOutTime());
        orderConfirmBookingParam.setRoomNum(orderConfirmBookingApiParam.getRoomNum());
        orderConfirmBookingParam.setOrderTotalPrice(new BigDecimal(orderConfirmBookingApiParam.getTotalPrice()).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        List<DailyQuotaInfo> dailyQuotaInfos = orderAdapter.confirmBooking(orderConfirmBookingParam);
        //返回结果
        List<RoomDayPriceDO> roomDayPriceDOList = roomDayPriceService.list(new QueryWrapper<RoomDayPriceDO>().lambda().eq(RoomDayPriceDO::getRoomId, orderConfirmBookingApiParam.getRoomId())
                .and(t -> t.between(RoomDayPriceDO::getDate, orderConfirmBookingApiParam.getCheckInTime(), orderConfirmBookingApiParam.getCheckOutTime())));
        List<OrderConfirmBookingVO> orderConfirmBookingVOList = dailyQuotaInfos.stream().map(dailyQuotaInfo -> {
            Optional<RoomDayPriceDO> dailyQuotaInfoOptional = roomDayPriceDOList.stream().filter(roomDayPriceDO -> dailyQuotaInfo.getDate().equals(DateUtil.format(roomDayPriceDO.getDate(), DateUtil.DEFAULT_DATE))).findFirst();
            return new OrderConfirmBookingVO(orderConfirmBookingApiParam.getHotelId(), orderConfirmBookingApiParam.getRoomId(), dailyQuotaInfo, dailyQuotaInfoOptional.isPresent() ? dailyQuotaInfoOptional.get() : null);
        }).collect(Collectors.toList());
        return new ResultSupport(true, orderConfirmBookingVOList, ErrorCode.SUCCESS);
    }

    private void validate(BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            for (ObjectError error : bindingResult.getAllErrors()) {
                if(Objects.equals(error.getCode(), "NotBlank")){
                    throw new BizException(ErrorCode.PARAM_EMPTY, error.getDefaultMessage());
                }else {
                    throw new BizException(ErrorCode.PARAM_ERROR, error.getDefaultMessage());
                }
            }
        }
    }

    /**
     * 预订
     * @param orderBookingApiParam
     * @return
     */
    @ApiOperation(value = "预订", httpMethod = "POST")
    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public Result<OrderBookingVO> booking(@RequestBody @Valid OrderBookingApiParam orderBookingApiParam, BindingResult bindingResult) {
        validate(bindingResult);
        RoomSourceInfoDO roomSourceInfoDO = roomSourceInfoService.getOne(new QueryWrapper<RoomSourceInfoDO>().lambda().eq(RoomSourceInfoDO::getRoomId, orderBookingApiParam.getRoomId()).and(i->i.eq(RoomSourceInfoDO::getHotelId, orderBookingApiParam.getHotelId())));
        if(roomSourceInfoDO == null) {
            throw new BizException(ErrorCode.PARAM_ERROR, "获取不到对应的数据，输入不存在的hotelId或是roomId");
        }
        try{
            OrderBookingParam orderBookingParam = new OrderBookingParam();
            orderBookingParam.setToken(channelAdapter.auth());
            orderBookingParam.setHotelNo(roomSourceInfoDO.getSupplierHotelId());
            orderBookingParam.setRoomTypeNo(roomSourceInfoDO.getSupplierRoomId());
            orderBookingParam.setInDate(orderBookingApiParam.getCheckInTime());
            orderBookingParam.setOutDate(orderBookingApiParam.getCheckOutTime());
            orderBookingParam.setRoomNum(orderBookingApiParam.getRoomNum());
            orderBookingParam.setLinkName(orderBookingApiParam.getGuestName());
            orderBookingParam.setMobile(orderBookingApiParam.getMobile());
            orderBookingParam.setOrderTotalPrice(new BigDecimal(orderBookingApiParam.getTotalPrice()).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
            orderBookingParam.setPayType(PayTypeEnum.B.getValue());
            orderBookingParam.setOutFlagNo(orderBookingApiParam.getOrderNo());
            String outOrderNo = orderAdapter.booking(orderBookingParam);
            return new ResultSupport(true, new OrderBookingVO(outOrderNo), ErrorCode.SUCCESS);
        }finally {
            //判定是否更新日态信息
            synchronousDataService.synRoomDayPrice(roomSourceInfoDO, orderBookingApiParam.getCheckInTime(), orderBookingApiParam.getCheckOutTime());
        }
    }

    /**
     * 获取订单详情
     * @param orderGetApiParam
     * @return
     */
    @ApiOperation(value = "获取订单详情", httpMethod = "POST")
    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.POST)
    public Result<OrderInfoVO> getOrderInfo(@ApiParam(value="订单编号") @RequestBody OrderGetApiParam orderGetApiParam) {
        orderGetApiParam.validate();
        String orderNo = orderGetApiParam.getOrderNo();
        OrderGetParam param = new OrderGetParam();
        param.setToken(channelAdapter.auth());
        param.setOutFlagNo(orderNo);
        OrderViewInfo orderViewInfo = orderAdapter.getOrderViewInfo(param);
        RoomSourceInfoDO roomSourceInfoDO = roomSourceInfoService.getOne(new QueryWrapper<RoomSourceInfoDO>()
                .lambda().eq(RoomSourceInfoDO::getSupplierHotelId, orderViewInfo.getHotelNo())
                .and(t -> t.eq(RoomSourceInfoDO::getSupplierRoomId, orderViewInfo.getRoomTypeCode())));
        //判定是否更新日态信息
        synchronousDataService.synRoomDayPrice(roomSourceInfoDO, DateUtil.format(DateUtil.parse(orderViewInfo.getInDate(), DateUtil.DEFAULT_DATE), DateUtil.DEFAULT_DATE),
                DateUtil.format(DateUtil.parse(orderViewInfo.getOutDate(), DateUtil.DEFAULT_DATE), DateUtil.DEFAULT_DATE));
        return new ResultSupport(true, new OrderInfoVO(orderNo, roomSourceInfoDO.getHotelId(), roomSourceInfoDO.getRoomId(), orderViewInfo),ErrorCode.SUCCESS);
    }

    /**
     * 取消订单
     * @param orderCancelApiParam
     * @return
     */
    @ApiOperation(value = "取消订单", httpMethod = "POST")
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public Result cancelOrder(@ApiParam("订单编号") @RequestBody OrderCancelApiParam orderCancelApiParam) {
        orderCancelApiParam.validate();
        String orderNo = orderCancelApiParam.getOrderNo();
        OrderCancelParam param = new OrderCancelParam();
        param.setToken(channelAdapter.auth());
        param.setOutFlagNo(orderNo);
        orderAdapter.cancelOrder(param);
        OrderGetApiParam orderGetApiParam = new OrderGetApiParam();
        orderGetApiParam.setOrderNo(orderNo);
        getOrderInfo(orderGetApiParam);
        return new ResultSupport(true,ErrorCode.SUCCESS);
    }
}
