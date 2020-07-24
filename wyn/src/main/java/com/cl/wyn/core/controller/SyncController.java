package com.cl.wyn.core.controller;

import com.cl.wyn.core.adapter.info.param.OrderCancelParam;
import com.cl.wyn.core.common.Assert;
import com.cl.wyn.core.common.ErrorCode;
import com.cl.wyn.core.common.Result;
import com.cl.wyn.core.common.ResultSupport;
import com.cl.wyn.core.service.ISynchronousDataService;
import com.cl.wyn.core.util.common.ListUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * by cl at 2020/7/3 0003
 */
@Api(tags = "同步接口")
@RequestMapping("sync")
@RestController
public class SyncController {
    @Autowired
    private ISynchronousDataService synchronousDataService;

    @ApiOperation(value = "同步区域", httpMethod = "POST")
    @RequestMapping(value = "/synDistrictInfo", method = RequestMethod.POST)
    public Result synDistrictInfo() {
        synchronousDataService.synDistrictInfo();
        return new ResultSupport(true, ErrorCode.SUCCESS);
    }
    @ApiOperation(value = "同步数据", httpMethod = "POST")
    @RequestMapping(value = "/synAll", method = RequestMethod.POST)
    public Result synHotel() {
        synchronousDataService.synAll();
        return new ResultSupport(true, ErrorCode.SUCCESS);
    }
    @ApiOperation(value = "同步数据;根据ots酒店id", httpMethod = "POST")
    @RequestMapping(value = "/synAllByHotelIds", method = RequestMethod.POST)
    public Result synHotelByHotelIds(@ApiParam("酒店ids") @RequestParam("hotelIds") String hotelIds) {
        Assert.notEmpty("hotelIds", hotelIds);
        List<String> hotelIdList = ListUtil.convertList(hotelIds, String.class);
        synchronousDataService.synByHotelIds(hotelIdList);
        return new ResultSupport(true, ErrorCode.SUCCESS);
    }
    @ApiOperation(value = "同步数据;根据来源酒店id", httpMethod = "POST")
    @RequestMapping(value = "/synAllBySourceHotelIds", method = RequestMethod.POST)
    public Result synHotelByHotelNos(@ApiParam("来源酒店ids") @RequestParam("sourceHotelIds") String sourceHotelIds) {
        Assert.notEmpty("sourceHotelIds", sourceHotelIds);
        List<String> sourceHotelIdList = ListUtil.convertList(sourceHotelIds, String.class);
        synchronousDataService.synBySourceHotelId(sourceHotelIdList);
        return new ResultSupport(true, ErrorCode.SUCCESS);
    }


}
