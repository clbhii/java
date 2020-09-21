package com.cl.wyn.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.cl.wyn.core.adapter.ICityAdapter;
import com.cl.wyn.core.adapter.IHotelAdapter;
import com.cl.wyn.core.adapter.IRoomAdapter;
import com.cl.wyn.core.adapter.info.*;
import com.cl.wyn.core.adapter.info.param.*;
import com.cl.wyn.core.common.BizException;
import com.cl.wyn.core.common.Contants;
import com.cl.wyn.core.common.ErrorCode;
import com.cl.wyn.core.entity.*;
import com.cl.wyn.core.enums.*;
import com.cl.wyn.core.service.*;
import com.cl.wyn.core.service.remote.ISchedulerApiService;
import com.cl.wyn.core.util.common.DateUtil;
import com.cl.wyn.core.util.common.StopWatch;
import com.cl.wyn.core.util.common.StringUtil;
import com.cl.wyn.core.util.common.ThreadPoolUtil;
import com.cl.wyn.core.util.json.JacksonBuilder;
import com.cl.wyn.core.util.json.JacksonUtils;
import com.cl.wyn.core.util.uuid.UUIDUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

/**
 * by cl at 2020/6/28 0028
 */
@Service
@Slf4j
public class SynchronousDataServiceImpl implements ISynchronousDataService {
    @Autowired
    private ICityAdapter cityAdapter;
    @Autowired
    private IDistrictInfoService districtInfoService;
    @Autowired
    @Qualifier("hotelAdapter")
    private IHotelAdapter hotelAdapter;
    @Autowired
    @Qualifier("roomAdapter")
    private IRoomAdapter roomAdapter;
    @Autowired
    private IHotelSourceInfoService hotelSourceInfoService;
    @Autowired
    private IHotelInfoService hotelInfoService;
    @Autowired
    private IHotelDetailInfoService hotelDetailInfoService;
    @Autowired
    private IHotelPicturesInfoService hotelPicturesInfoService;
    @Autowired
    private IHotelExternalFacilitiesInfoService hotelExternalFacilitiesInfoService;
    @Autowired
    private IHotelTagsInfoService hotelTagsInfoService;
    @Autowired
    private IRoomTypeInfoService roomTypeInfoService;
    @Autowired
    private IRoomTypeSourceInfoService roomTypeSourceInfoService;
    @Autowired
    private IRoomTypePicturesInfoService roomTypePicturesInfoService;
    @Autowired
    private IRoomInfoService roomInfoService;
    @Autowired
    private IRoomSourceInfoService roomSourceInfoService;
    @Autowired
    private IRoomDayPriceService roomDayPriceService;
    @Autowired
    private ISyncRecordService syncRecordService;
    @Autowired
    private IRoomCancelRuleInfoService roomCancelRuleInfoService;
    @Autowired
    private TransactionDefinition transactionDefinition;
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private ISchedulerApiService schedulerApiService;

    @Value("${wyn.sourceId}")
    private String sourceId;

    private ObjectMapper objectMapper = new JacksonBuilder().build();

    private Semaphore semaphore = new Semaphore(1);

    @Override
    public void synDistrictInfo() {
        log.info("开始同步区域信息");
        StopWatch stopWatch = new StopWatch();
        List<ProvinceCityInfo> provinceCityList = cityAdapter.getProvinceCityList(new BaseParam());
        List<DistrictInfoDO> districtInfoDOList = districtInfoService.list(new QueryWrapper<DistrictInfoDO>().lambda().nested(i -> i.eq(DistrictInfoDO::getIsWyn88Synchronized, YesNoEnum.YES.getValue()))
                .and(i -> i.eq(DistrictInfoDO::getIsDeleted, YesNoEnum.NO.getValue())));
        Map<String, DistrictInfoDO> districtInfoDOMap = districtInfoDOList.stream().collect(Collectors.toMap(DistrictInfoDO::getCode, t -> t));
        List<DistrictInfoDO> insertList = new ArrayList<>();
        provinceCityList.stream().forEach(provinceCityInfo -> {
            String provinceNo = provinceCityInfo.getProvinceNo() + "000000";
            String cityNo = provinceCityInfo.getCityNo() + "000000";
            //省
            if (!districtInfoDOMap.containsKey(provinceNo)) {
                DistrictInfoDO proviceDistrictInfoDO = new DistrictInfoDO();
                proviceDistrictInfoDO.setId(UUIDUtil.randomID());
                proviceDistrictInfoDO.setCode(provinceNo);
                proviceDistrictInfoDO.setName(provinceCityInfo.getProvinceName());
                proviceDistrictInfoDO.setParentId(Contants.DISTRICT_ROOT_ID);
                proviceDistrictInfoDO.setParentCode(Contants.DISTRICT_ROOT_CODE);
                proviceDistrictInfoDO.setIsWyn88Synchronized(YesNoEnum.YES.getValue());
                proviceDistrictInfoDO.createDeFault();
                insertList.add(proviceDistrictInfoDO);
                districtInfoDOMap.put(provinceNo, proviceDistrictInfoDO);
            }
            //城市
            if (!districtInfoDOMap.containsKey(cityNo)) {
                DistrictInfoDO cityDistrictInfoDO = new DistrictInfoDO();
                cityDistrictInfoDO.setId(UUIDUtil.randomID());
                cityDistrictInfoDO.setCode(cityNo);
                cityDistrictInfoDO.setName(provinceCityInfo.getCityName());
                cityDistrictInfoDO.setParentId(districtInfoDOMap.get(provinceNo).getId());
                cityDistrictInfoDO.setParentCode(provinceNo);
                cityDistrictInfoDO.setIsWyn88Synchronized(YesNoEnum.YES.getValue());
                cityDistrictInfoDO.createDeFault();
                insertList.add(cityDistrictInfoDO);
                districtInfoDOMap.put(cityNo, cityDistrictInfoDO);
            }
        });
        districtInfoService.insertBatch(insertList);
        log.info("同步区域信息完成，耗时{}ms", stopWatch.elapsedTime());
    }



    @Override
    public void synAll() {
        synAll(null, null, 30);
    }

    @Override
    public void synByHotelIds(List<String> hotelIdList) {
        synAll(hotelIdList, null, 30);
    }

    @Override
    public void synBySourceHotelId(List<String> sourceHotelIdList) {
        synAll(null ,sourceHotelIdList, 30);
    }

    @Override
    public void synAll(int day) {
        synAll(null, null, day);
    }

    private void synAll(List<String> hotelIdList, List<String> sourceHotelIdList, int day)  {
        if(!semaphore.tryAcquire()){
            log.info("当前有同步任务，暂不执行");
            return;
        }
        log.info("开始同步信息");
        try{
            StopWatch stopWatch = new StopWatch();
            SyncRecordDO syncRecordDO = new SyncRecordDO();
            syncRecordDO.setDateStart(new Date());
            //同步区域
            synDistrictInfo();
            //同步酒店
            Map<String, String> failMap = synHotel(hotelIdList, sourceHotelIdList, day);
            //失败重试
            if(failMap.size() > 0) {
                List<String> failSourceHotelIdList = failMap.keySet().stream().collect(Collectors.toList());
                failMap = synHotel(null, failSourceHotelIdList, day);
            }
            syncRecordDO.setDateEnd(new Date());
            if(failMap.size() > 0) {
                syncRecordDO.setResult(SyncResultEnum.FAIL.getValue());
                syncRecordDO.setRemark(JacksonUtils.objectToJson(objectMapper, failMap));
            }else {
                syncRecordDO.setResult(SyncResultEnum.SUCCESS.getValue());
            }
            syncRecordService.save(syncRecordDO);
            log.info("同步信息完成，耗时{}ms", stopWatch.elapsedTime());
            if(SyncResultEnum.SUCCESS.getValue().equals(syncRecordDO.getResult())){
                Map<String, String> map = new HashMap<>();
                map.put("type", day + "");
                schedulerApiService.syncFliggyHotelInfo(map);
            }
        }catch (Exception e) {
            throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR, e);
        }finally {
            semaphore.release();
        }

    }

    private Map<String, String> synHotel(List<String> syncHotelIdList, List<String> syncSourceHotelIdList, int day) {

        log.info("开始同步酒店信息");
        StopWatch stopWatch = new StopWatch();
        Map<String, String> failMap = new ConcurrentHashMap<>();

        List<DistrictInfoDO> districtInfoDOList = districtInfoService.list(new QueryWrapper<DistrictInfoDO>().lambda().nested(i -> i.eq(DistrictInfoDO::getIsWyn88Synchronized, YesNoEnum.YES.getValue()))
                .and(i -> i.eq(DistrictInfoDO::getIsDeleted, YesNoEnum.NO.getValue())));
        Map<String, DistrictInfoDO> districtInfoDOMap = districtInfoDOList.stream().collect(Collectors.toMap(DistrictInfoDO::getCode, t -> t));

        //酒店来源信息
        LambdaQueryWrapper<HotelSourceInfoDO> hotelSourceInfoDOLambdaQueryWrapper = new QueryWrapper<HotelSourceInfoDO>().lambda().nested(t -> t.eq(HotelSourceInfoDO::getSubSourceType, SubSourceTypeEnum.WYN.getValue()))
                .and(t -> t.eq(HotelSourceInfoDO::getIsDeleted, YesNoEnum.NO.getValue()));
        if (syncHotelIdList != null && syncHotelIdList.size() > 0) {
            hotelSourceInfoDOLambdaQueryWrapper.and(i -> i.in(HotelSourceInfoDO::getHotelId, syncHotelIdList));
        }
        if (syncSourceHotelIdList != null && syncSourceHotelIdList.size() > 0) {
            hotelSourceInfoDOLambdaQueryWrapper.and(i -> i.in(HotelSourceInfoDO::getSourceHotelId, syncSourceHotelIdList));
        }
        List<HotelSourceInfoDO> hotelSourceInfoDOList = hotelSourceInfoService.list(hotelSourceInfoDOLambdaQueryWrapper);
        Map<String, String> sourceHotelIdToHotelIdMap =  new HashMap<>();
        Map<String, HotelSourceInfoDO> hotelSourceInfoDOMap = new HashMap<>();
        List<String> hotelIdList = new ArrayList<>();
        hotelSourceInfoDOList.stream().forEach(hotelSourceInfoDO -> {
            sourceHotelIdToHotelIdMap.put(hotelSourceInfoDO.getSourceHotelId(), hotelSourceInfoDO.getHotelId());
            hotelSourceInfoDOMap.put(hotelSourceInfoDO.getHotelId(),hotelSourceInfoDO);
            hotelIdList.add(hotelSourceInfoDO.getHotelId());
        });
        //酒店信息
        List<HotelInfoDO> hotelInfoDOList = new ArrayList<>();
        List<HotelDetailInfoDO> hotelDetailInfoDOList = new ArrayList<>();
        List<HotelPicturesInfoDO> hotelPicturesInfoDOList = new ArrayList<>();
        List<HotelExternalFacilitiesInfoDO> hotelExternalFacilitiesInfoDOList = new ArrayList<>();
        List<HotelTagsInfoDO> hotelTagsInfoDOList = new ArrayList<>();
        List<RoomTypeSourceInfoDO> roomTypeSourceInfoDOList = new ArrayList<>();
        if (hotelIdList != null && hotelIdList.size() > 0) {
            hotelInfoDOList = hotelInfoService.list(new QueryWrapper<HotelInfoDO>().lambda().nested(i -> i.in(HotelInfoDO::getId, hotelIdList)));
            hotelDetailInfoDOList = hotelDetailInfoService.list(new QueryWrapper<HotelDetailInfoDO>().lambda().nested(i -> i.in(HotelDetailInfoDO::getHotelId, hotelIdList)));
            hotelPicturesInfoDOList = hotelPicturesInfoService.list(new QueryWrapper<HotelPicturesInfoDO>().lambda().nested(i -> i.in(HotelPicturesInfoDO::getHotelId, hotelIdList)).and(i->i.eq(HotelPicturesInfoDO::getIsDeleted, YesNoEnum.NO.getValue() )));
            hotelExternalFacilitiesInfoDOList = hotelExternalFacilitiesInfoService.list(new QueryWrapper<HotelExternalFacilitiesInfoDO>().lambda().nested(i -> i.in(HotelExternalFacilitiesInfoDO::getHotelId, hotelIdList)));
            hotelTagsInfoDOList = hotelTagsInfoService.list(new QueryWrapper<HotelTagsInfoDO>().lambda().nested(i -> i.in(HotelTagsInfoDO::getHotelId, hotelIdList)));
            roomTypeSourceInfoDOList = roomTypeSourceInfoService.list(new QueryWrapper<RoomTypeSourceInfoDO>().lambda().nested(t -> t.eq(RoomTypeSourceInfoDO::getIsDeleted, YesNoEnum.NO.getValue()))
                    .and(t -> t.in(RoomTypeSourceInfoDO::getHotelId, hotelIdList)));
        }

        Map<String, HotelInfoDO> hotelInfoDOMap = hotelInfoDOList.stream().collect(Collectors.toMap(HotelInfoDO::getId, t -> t));
        Map<String, HotelDetailInfoDO> hotelDetailInfoDOMap = hotelDetailInfoDOList.stream().collect(Collectors.toMap(HotelDetailInfoDO::getHotelId, t -> t));
        Map<String, List<HotelPicturesInfoDO>> hotelPicturesInfoListMap = hotelPicturesInfoDOList.stream().collect(Collectors.groupingBy(HotelPicturesInfoDO::getHotelId));
        Map<String, List<HotelExternalFacilitiesInfoDO>> hotelExternalFacilitiesInfoListMap = hotelExternalFacilitiesInfoDOList.stream().collect(Collectors.groupingBy(HotelExternalFacilitiesInfoDO::getHotelId));
        Map<String, List<HotelTagsInfoDO>> hotelTagsInfoListMap = hotelTagsInfoDOList.stream().collect(Collectors.groupingBy(HotelTagsInfoDO::getHotelId));

        //物理房型来源信息
        Map<String, String> sourceRoomTypeIdToRoomTypeIdMap = new HashMap<>();
        Map<String, List<String>> hotelIdAndRoomTypeIdListMap = new HashMap<>();
        roomTypeSourceInfoDOList.stream().forEach(roomTypeSourceInfoDO -> {
            sourceRoomTypeIdToRoomTypeIdMap.put(roomTypeSourceInfoDO.getSourceHotelId() + roomTypeSourceInfoDO.getSourceRoomTypeId(), roomTypeSourceInfoDO.getRoomTypeId());
            List<String> roomTypeIdList = hotelIdAndRoomTypeIdListMap.get(roomTypeSourceInfoDO.getHotelId());
            if(roomTypeIdList == null) {
                roomTypeIdList = new ArrayList<>();
                hotelIdAndRoomTypeIdListMap.put(roomTypeSourceInfoDO.getHotelId(), roomTypeIdList);
            }
            roomTypeIdList.add(roomTypeSourceInfoDO.getRoomTypeId());
        });


        Collection<String> roomTypeIdList = sourceRoomTypeIdToRoomTypeIdMap.values();
        //物理房型
        List<RoomTypeInfoDO> roomTypeInfoDOList = new ArrayList<>();
        List<RoomTypePicturesInfoDO> roomTypePicturesInfoDOList = new ArrayList<>();
        List<RoomSourceInfoDO> roomSourceInfoDOList = new ArrayList<>();
        if (roomTypeIdList != null && roomTypeIdList.size() > 0) {
            roomTypeInfoDOList = roomTypeInfoService.list(new QueryWrapper<RoomTypeInfoDO>().lambda().nested(i -> i.in(RoomTypeInfoDO::getId, roomTypeIdList)));
            roomTypePicturesInfoDOList = roomTypePicturesInfoService.list(new QueryWrapper<RoomTypePicturesInfoDO>().lambda().nested(i -> i.in(RoomTypePicturesInfoDO::getRoomTypeId, roomTypeIdList)));
            roomSourceInfoDOList = roomSourceInfoService.list(new QueryWrapper<RoomSourceInfoDO>().lambda().nested(i -> i.in(RoomSourceInfoDO::getRoomTypeId, roomTypeIdList)));
        }
        Map<String, RoomTypeInfoDO> roomTypeInfoDOMap = roomTypeInfoDOList.stream().collect(Collectors.toMap(RoomTypeInfoDO::getId, t -> t));
        Map<String, List<RoomTypePicturesInfoDO>> roomTypePicturesInfoDOListMap = roomTypePicturesInfoDOList.stream().collect(Collectors.groupingBy(RoomTypePicturesInfoDO::getRoomTypeId));

        //售卖房型来源
        Map<String, String> roomTypeIdToRoomIdMap = new HashMap<>();
        Map<String, List<String>> hotelIdAndRoomIdListMap = new HashMap<>();
        List<String> allRoomIdList = new ArrayList<>();
        roomSourceInfoDOList.stream().forEach(roomSourceInfoDO -> {
            roomTypeIdToRoomIdMap.put(roomSourceInfoDO.getRoomTypeId(), roomSourceInfoDO.getRoomId());
            List<String> roomIdList = hotelIdAndRoomIdListMap.get(roomSourceInfoDO.getHotelId());
            if(roomIdList == null) {
                roomIdList = new ArrayList<>();
                hotelIdAndRoomIdListMap.put(roomSourceInfoDO.getHotelId(), roomIdList);
            }
            roomIdList.add(roomSourceInfoDO.getRoomId());
            allRoomIdList.add(roomSourceInfoDO.getRoomId());
        });

        List<RoomInfoDO> roomInfoDOList = new ArrayList<>();
        List<RoomCancelRuleInfoDO> roomCancelRuleInfoDOList = new ArrayList<>();
        if (allRoomIdList != null && roomTypeIdToRoomIdMap.size() > 0) {
            roomInfoDOList = roomInfoService.list(new QueryWrapper<RoomInfoDO>().lambda().nested(i -> i.in(RoomInfoDO::getId, allRoomIdList)));
            roomCancelRuleInfoDOList = roomCancelRuleInfoService.list(new QueryWrapper<RoomCancelRuleInfoDO>().lambda().nested(i -> i.in(RoomCancelRuleInfoDO::getRoomId, allRoomIdList)));
        }
        Map<String, RoomInfoDO> roomInfoDOMap = roomInfoDOList.stream().collect(Collectors.toMap(RoomInfoDO::getId, t -> t));
        Map<String, List<RoomCancelRuleInfoDO>> roomCancelRuleInfoDOListMap = roomCancelRuleInfoDOList.stream().collect(Collectors.groupingBy(RoomCancelRuleInfoDO::getRoomId));
        List<HotelInfo> hotelInfoList = getHotelInfo(syncHotelIdList, syncSourceHotelIdList, hotelSourceInfoDOMap);
//        hotelInfoList = hotelInfoList.subList(0,100);
        log.info("同步酒店信息,准备工作耗时:" + stopWatch.elapsedMiddleTime());
        final int pageSize = 1000;
        int size = hotelInfoList.size();
        final int num = size%pageSize == 0 ? size/pageSize : size/pageSize + 1;
        final CountDownLatch countDownLatch = new CountDownLatch(num);
        for(int i = 0; i < num; i++) {
            int end =  size < (i + 1)*pageSize ? size : (i + 1)*pageSize;
            final List<HotelInfo> hotelInfoList1 = hotelInfoList.subList(i*pageSize , end);;
            ThreadPoolUtil.threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    log.info("处理数据：" + JacksonUtils.objectToJson(objectMapper, hotelInfoList1));
                    hotelInfoList1.forEach(hotelInfo -> {
                        if (StringUtil.isEmpty(hotelInfo.getHotelNo())) {
                            log.error("数据有问题不同步，hotelInfo = ", JacksonUtils.objectToJson(objectMapper, hotelInfo));
                            return;
                        }
                        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
                        try {
                            //房态
                            RoomStatusParam roomStatusParam = new RoomStatusParam();
                            roomStatusParam.setHotelNo(hotelInfo.getHotelNo());
                            Date date = new Date();
                            String inDate = DateUtil.format(date, DateUtil.DEFAULT_DATE);
                            String outDate = DateUtil.format(DateUtil.addDay(date, day), DateUtil.DEFAULT_DATE);
                            roomStatusParam.setInDate(inDate);
                            roomStatusParam.setOutDate(outDate);
                            roomStatusParam.setRatePlanList(RatePlanEnum.DisRate.getValue());
                            List<RoomStatusInfo> roomStatusInfoList = roomAdapter.roomStatus(roomStatusParam);
                            //酒店
                            String hotelId = dealHotelInfo(hotelInfo, roomStatusInfoList, sourceHotelIdToHotelIdMap, hotelInfoDOMap, districtInfoDOMap);
                            //酒店来源信息
                            dealHotelSource(hotelId, hotelSourceInfoDOMap.get(hotelId), hotelInfo.getHotelNo());
                            //详情
                            dealHotelDetail(hotelId, hotelDetailInfoDOMap.get(hotelId), hotelInfo.getSummary());
                            //设施
                            HotelInfo.FacilityServiceInfo facilityServiceInfo = hotelInfo.getFacilityServiceInfo();
                            if(facilityServiceInfo == null) {
                                facilityServiceInfo = new HotelInfo.FacilityServiceInfo();
                            }
                            dealFacilityService(hotelId, hotelExternalFacilitiesInfoListMap.get(hotelId), FacilitiesCategoryEnum.PARKING, facilityServiceInfo.getParking());
                            //标签
                            dealHotelTags(hotelId, hotelTagsInfoListMap.get(hotelId), facilityServiceInfo);
                            //图片
                            dealHotelPictures(hotelId, hotelInfo, hotelPicturesInfoListMap.get(hotelId));

                            //房型
                            HotelGetRoomTypeParam param = new HotelGetRoomTypeParam();
                            param.setHotelNo(hotelInfo.getHotelNo());
                            List<RoomTypeInfo> roomTypeInfoList = hotelAdapter.getRoomType(param);
                            roomTypeInfoList = distinctRoomTypeInfo(hotelInfo.getHotelNo(), roomTypeInfoList);
                            //房型图片
                            HotelGetRoomTypePicParam hotelGetRoomTypePicParam = new HotelGetRoomTypePicParam();
                            hotelGetRoomTypePicParam.setHotelNo(hotelInfo.getHotelNo());
                            List<RoomTypePicInfo> roomTypePicInfoList = hotelAdapter.getRoomTypePic(hotelGetRoomTypePicParam);
                            Map<String, List<RoomTypePicInfo>> roomTypePicInfoListMap = roomTypePicInfoList.stream().collect(Collectors.groupingBy(RoomTypePicInfo::getRoomTypeNo));

                            Map<String, List<RoomStatusInfo>> roomStatusInfoListMap = roomStatusInfoList.stream().collect(Collectors.groupingBy(RoomStatusInfo::getRoomTypeCode));

                            List<RoomDayPriceDO> saveRoomDayPriceDOList = new ArrayList<>();
                            List<RoomTypeInfoDO> saveRoomTypeInfoDOList = new ArrayList<>();
                            List<RoomTypeSourceInfoDO> saveRoomTypeSourceInfoDOList = new ArrayList<>();
                            List<RoomTypePicturesInfoDO> saveRoomTypePicturesInfoDOList = new ArrayList<>();
                            List<RoomInfoDO> saveRoomInfoDOList = new ArrayList<>();
                            List<RoomSourceInfoDO> saveRoomSourceInfoDO = new ArrayList<>();
                            List<RoomCancelRuleInfoDO> saveRoomCancelRuleInfoDOList = new ArrayList<>();
                            HotelInfo.FacilityServiceInfo finalFacilityServiceInfo = facilityServiceInfo;

                            List<String> newRoomTypeIdList = new ArrayList<>();
                            roomTypeInfoList.stream().forEach(roomTypeInfo -> {
                                //物理房型
                                String roomTypeId = dealRoomTypeInfo(hotelId, hotelInfo, roomTypeInfo, finalFacilityServiceInfo, sourceRoomTypeIdToRoomTypeIdMap, roomTypeInfoDOMap, saveRoomTypeInfoDOList);
                                newRoomTypeIdList.add(roomTypeId);
                                //物理房型来源
                                dealRoomTypeSource(hotelId, roomTypeId, hotelInfo, roomTypeInfo, sourceRoomTypeIdToRoomTypeIdMap, saveRoomTypeSourceInfoDOList);
                                //物理房型图片
                                dealRoomTypePictures(roomTypeId, roomTypePicInfoListMap.get(roomTypeInfo.getRoomTypeCode()), roomTypePicturesInfoDOListMap.get(roomTypeId), saveRoomTypePicturesInfoDOList);

                                //售卖房型
                                String roomId = dealRoomInfo(hotelId, roomTypeId, hotelInfo, roomTypeInfo, finalFacilityServiceInfo, roomTypeIdToRoomIdMap, roomInfoDOMap, saveRoomInfoDOList);
                                //售卖房型来源
                                dealRoomSource(hotelId, roomTypeId, roomId, hotelInfo, roomTypeInfo, roomTypeIdToRoomIdMap, saveRoomSourceInfoDO);
                                //取消规则
                                dealRoomCancelRuleInfo(roomId,roomCancelRuleInfoDOListMap, saveRoomCancelRuleInfoDOList);
                                //每日价格
                                dealDayPrice(hotelId, roomTypeId, roomId, hotelInfo, roomTypeInfo, roomStatusInfoListMap, saveRoomDayPriceDOList);

                            });
                            roomTypeInfoService.insertBatch(saveRoomTypeInfoDOList);
                            roomTypeSourceInfoService.insertBatch(saveRoomTypeSourceInfoDOList);
                            roomTypePicturesInfoService.insertBatch(saveRoomTypePicturesInfoDOList);
                            roomInfoService.insertBatch(saveRoomInfoDOList);
                            roomSourceInfoService.insertBatch(saveRoomSourceInfoDO);
                            roomCancelRuleInfoService.insertBatch(saveRoomCancelRuleInfoDOList);

                            List<String> deleteRoomIdList = hotelIdAndRoomIdListMap.get(hotelId);
                            if(deleteRoomIdList != null && deleteRoomIdList.size() > 0)
                                roomDayPriceService.remove(new QueryWrapper<RoomDayPriceDO>().lambda().in(RoomDayPriceDO::getRoomId, deleteRoomIdList).and(i-> i.lt(RoomDayPriceDO::getDate, outDate)));
                            roomDayPriceService.insertBatch(saveRoomDayPriceDOList);
                            //删除房型
                            deleteRoomType(hotelIdAndRoomTypeIdListMap.get(hotelId), newRoomTypeIdList, roomTypeIdToRoomIdMap);

                            transactionManager.commit(transactionStatus);
                        } catch (Exception e) {
                            String errorLog = "同步数据失败，hotelNo = " + hotelInfo.getHotelNo();
                            log.error(errorLog, e);
                            failMap.put(hotelInfo.getHotelNo(), errorLog + e.getMessage());
                            try{
                                transactionManager.rollback(transactionStatus);
                            }catch(Exception e1) {
                                log.error(e1.getMessage(), e1);
                            }

                        }
                    });
                    countDownLatch.countDown();
                }
            });
        }
        try{
            countDownLatch.await();
        }catch (Exception e) {
            log.info(e.getMessage(), e);
        }
        deleteHotel(sourceHotelIdToHotelIdMap, hotelInfoList, hotelIdAndRoomTypeIdListMap, roomTypeIdToRoomIdMap);
        log.info("同步酒店信息完成，耗时{}ms,{}ms,失败信息:{}", stopWatch.elapsedTime(),stopWatch.elapsedMiddleTime(), JacksonUtils.objectToJson(objectMapper, failMap));
        return failMap;
    }

    private void deleteHotel(Map<String, String> sourceHotelIdToHotelIdMap , List<HotelInfo> hotelInfoList, Map<String, List<String>> hotelIdAndRoomTypeIdListMap, Map<String, String> roomTypeIdToRoomIdMap){
        if(sourceHotelIdToHotelIdMap == null) {
            return;
        }
        List<String> deleteList = new ArrayList<>();
        for(String sourceHotelId : sourceHotelIdToHotelIdMap.keySet()){
            boolean flag = false;
            for(HotelInfo hotelInfo : hotelInfoList){
                if(sourceHotelId.equals(hotelInfo.getHotelNo())){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                deleteList.add(sourceHotelIdToHotelIdMap.get(sourceHotelId));
            }
        }
        for(String hotelId : deleteList) {
            hotelSourceInfoService.deleteByHotelId(hotelId);
            hotelInfoService.deleteByHotelId(hotelId);
            hotelDetailInfoService.deleteByHotelId(hotelId);
            hotelExternalFacilitiesInfoService.deleteByHotelId(hotelId);
            hotelTagsInfoService.deleteByHotelId(hotelId);
            hotelPicturesInfoService.deleteByHotelId(hotelId);
            List<String> roomTypeIdList = hotelIdAndRoomTypeIdListMap.get(hotelId);
            deleteRoomType(roomTypeIdList, roomTypeIdToRoomIdMap);
        }

    }

    private void deleteRoomType(List<String> oldRoomTypeIdList, List<String>  newRoomTypeIdList, Map<String, String> roomTypeIdToRoomIdMap){
        if(oldRoomTypeIdList == null) {
            return;
        }
        List<String> deleteList =new ArrayList<>();
        for(String oldRoomTypeId : oldRoomTypeIdList){
            if(!newRoomTypeIdList.contains(oldRoomTypeId)){
                deleteList.add(oldRoomTypeId);
            }
        }
        deleteRoomType(deleteList, roomTypeIdToRoomIdMap);
    }

    private void deleteRoomType(List<String> deleteList,  Map<String, String> roomTypeIdToRoomIdMap) {
        if(deleteList == null) {
            return;
        }
        for(String roomTypeId : deleteList) {
            roomTypeSourceInfoService.deleteByRoomTypeId(roomTypeId);
            roomTypeInfoService.deleteByRoomTypeId(roomTypeId);
            roomTypePicturesInfoService.deleteByRoomTypeId(roomTypeId);
            String roomId = roomTypeIdToRoomIdMap.get(roomTypeId);
            roomSourceInfoService.deleteByRoomId(roomId);
            roomInfoService.deleteByRoomId(roomId);
            roomCancelRuleInfoService.deleteByRoomId(roomId);
            roomDayPriceService.remove(new QueryWrapper<RoomDayPriceDO>().lambda().in(RoomDayPriceDO::getRoomId, roomId));
        }
    }


    private List<HotelInfo> getHotelInfo(List<String> syncHotelIdList, List<String> syncSourceHotelIdList, Map<String, HotelSourceInfoDO> hotelSourceInfoDOMap) {
        List<HotelInfo> hotelInfoList = new ArrayList<>();
        if (syncHotelIdList != null && syncHotelIdList.size() > 0) {
            for(String hotelId : syncHotelIdList) {
                HotelSourceInfoDO hotelSourceInfoDO = hotelSourceInfoDOMap.get(hotelId);
                if(hotelSourceInfoDO == null) {
                    throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR, "没有这个酒店；hotelId=" + hotelId);
                }
                HotelGetModelParam param = new HotelGetModelParam();
                param.setHotelCode(hotelSourceInfoDO.getSourceHotelId());
                hotelInfoList.add(hotelAdapter.getModel(param));
            }
        } else if (syncSourceHotelIdList != null && syncSourceHotelIdList.size() > 0) {
            for(String sourceHotelId : syncSourceHotelIdList) {
                HotelGetModelParam param = new HotelGetModelParam();
                param.setHotelCode(sourceHotelId);
                hotelInfoList.add(hotelAdapter.getModel(param));
            }
        }else {
            HotelGetListParam param = new HotelGetListParam();
            param.setPageSize(Contants.PAGE_SIZE.toString());
            PageList<HotelInfo> hotelInfoPageList = hotelAdapter.getList(param);
            hotelInfoList = hotelInfoPageList.getRows();
            Integer total = hotelInfoPageList.getTotal();
            for (int i = 0; i < total / Contants.PAGE_SIZE; i++) {
                param.setPageIndex((i + 2) + "");
                hotelInfoPageList = hotelAdapter.getList(param);
                hotelInfoList.addAll(hotelInfoPageList.getRows());
            }
        }
        hotelInfoList = distinctHotelInfo(hotelInfoList);
        return hotelInfoList;
    }

    public List<HotelInfo> distinctHotelInfo(List<HotelInfo> hotelInfoList) {
        StopWatch stopWatch = new StopWatch();
        List<HotelInfo> result = new ArrayList<>();
        Map<Integer, Integer> flag = new HashMap<>();
        Map<String, List<String>> distinctHotelIdMap = new HashMap<>();
        for(int i = 0; i < hotelInfoList.size(); i ++) {
            if(flag.get(i) != null){
                continue;
            }
            HotelInfo hotelInfo = hotelInfoList.get(i);
            for (int j = i + 1; j < hotelInfoList.size(); j ++) {
                if(flag.get(j) != null){
                    continue;
                }
                HotelInfo hotelInfo1 = hotelInfoList.get(j);
                if(hotelInfo.equals(hotelInfo1)){
                    flag.put(j,i);
                    List<String> stringList = distinctHotelIdMap.get(hotelInfo.getHotelNo());
                    if(stringList == null){
                        stringList = new ArrayList<>();
                        distinctHotelIdMap.put(hotelInfo.getHotelNo(), stringList);
                    }
                    stringList.add(hotelInfo1.getHotelNo());
                }
            }
            result.add(hotelInfo);
        }
        log.info("酒店去重耗时：" + stopWatch.elapsedTime() + ";重复记录:" + distinctHotelIdMap);
        return result;
    }

    public List<RoomTypeInfo> distinctRoomTypeInfo(String hotelNo, List<RoomTypeInfo> roomTypeInfoList) {
        List<RoomTypeInfo> result = new ArrayList<>();
        Map<Integer, Integer> flag = new HashMap<>();
        Map<String, List<String>> distinctRoomIdMap = new HashMap<>();
        for (int i = 0; i < roomTypeInfoList.size(); i++) {
            if (flag.get(i) != null ) {
                continue;
            }
            RoomTypeInfo roomTypeInfo = roomTypeInfoList.get(i);
            for (int j = i + 1; j < roomTypeInfoList.size(); j++) {
                if (flag.get(j) != null) {
                    continue;
                }
                RoomTypeInfo roomTypeInfo1 = roomTypeInfoList.get(j);
                if (roomTypeInfo.getRoomTypeName().equals(roomTypeInfo1.getRoomTypeName())) {
                    flag.put(j, i);
                    List<String> stringList = distinctRoomIdMap.get(roomTypeInfo.getRoomTypeCode());
                    if (stringList == null) {
                        stringList = new ArrayList<>();
                        distinctRoomIdMap.put(roomTypeInfo.getRoomTypeCode(), stringList);
                    }
                    stringList.add(roomTypeInfo1.getRoomTypeCode());
                }
            }
            result.add(roomTypeInfo);
        }
        if (distinctRoomIdMap.size() > 0)
            log.info("酒店房型去重;hotelNo=" + hotelNo + ";重复记录:" + distinctRoomIdMap);
        return result;
    }

    /**
     * 处理酒店
     * @param hotelInfo
     * @param roomStatusInfoList
     * @param sourceHotelIdToHotelIdMap
     * @param hotelInfoDOMap
     * @param districtInfoDOMap
     * @return
     */
    private String dealHotelInfo( HotelInfo hotelInfo, List<RoomStatusInfo> roomStatusInfoList, Map<String, String> sourceHotelIdToHotelIdMap, Map<String, HotelInfoDO> hotelInfoDOMap, Map<String, DistrictInfoDO> districtInfoDOMap) {
        String hotelId = sourceHotelIdToHotelIdMap.get(hotelInfo.getHotelNo());
        HotelInfoDO hotelInfoDO = null;
        if (StringUtil.notEmpty(hotelId)) {
            //更新
            hotelInfoDO = hotelInfoDOMap.get(hotelId);
            hotelInfoDO.updateDefault();
        } else {
            hotelInfoDO = new HotelInfoDO();
            String uuId = UUIDUtil.randomUUID();
            hotelInfoDO.setId(UUIDUtil.randomID());
            hotelInfoDO.setHotelCode(uuId);
            hotelInfoDO.createDeFault();
        }
        hotelInfoDO.setHotelName(hotelInfo.getHotelName());
        //省 市
        DistrictInfoDO cityDistrictInfoDO = districtInfoDOMap.get(hotelInfo.getCity() + "000000");
        if (cityDistrictInfoDO == null) {
            throw new RuntimeException("没有区域信息，cityCode=" + hotelInfo.getCity());
        }
        hotelInfoDO.setProvince(cityDistrictInfoDO.getParentCode());
        hotelInfoDO.setCity(cityDistrictInfoDO.getCode());
        hotelInfoDO.setAddress(hotelInfo.getAddress());
        hotelInfoDO.setLongitude(hotelInfo.getLongitude());
        hotelInfoDO.setLatitude(hotelInfo.getLatitude());
        hotelInfoDO.setStarRating(hotelInfo.getStarLevel());
        hotelInfoDO.setTel(hotelInfo.getTel());
        //酒店品牌
        hotelInfoDO.setBrandName(BrandEnum.getDesc(hotelInfo.getBrand()));
        String currentDate = DateUtil.format(new Date(), DateUtil.DEFAULT_DATE);
        BigDecimal minPrice = null;
        for(RoomStatusInfo roomStatusInfo : roomStatusInfoList){
            if(currentDate.equals(roomStatusInfo.getBizDay())){
                List<RoomStatusInfo.RatePlanInfo> ratePlanInfoList = roomStatusInfo.getRatePlanInfoList();
                if(ratePlanInfoList != null && ratePlanInfoList.size() > 0) {
                    RoomStatusInfo.RatePlanInfo ratePlanInfo = ratePlanInfoList.get(0);
                    if(minPrice == null) {
                        minPrice = ratePlanInfo.getDisRate();
                    }else if(minPrice.compareTo(ratePlanInfo.getDisRate()) > 0){
                        minPrice = ratePlanInfo.getDisRate();
                    }
                }

            }
        }
        //酒店当日最低加
        hotelInfoDO.setLowPrice(minPrice);
        hotelInfoDO.setCover(hotelInfo.getImageUrl());
        //开业时间
        hotelInfoDO.setOpenYear(hotelInfo.getOpeningTime());
        hotelInfoDO.setRoomQuantity(hotelInfo.getRoomCount());
        //实际已定
        hotelInfoDO.setRealBookedRoomQuantity(0);
        hotelInfoDO.setVirtualBookedRoomQuantity(0);
        //是否有外部设施
        hotelInfoDO.setIsHasExternalFacilities(judgeIsHasExternalFacilities(hotelInfo.getFacilityServiceInfo()));
        if (StringUtil.notEmpty(hotelId)) {
            hotelInfoService.updateById(hotelInfoDO);
        } else {
            hotelInfoService.save(hotelInfoDO);
        }
        return hotelInfoDO.getId();
    }

    private Integer judgeIsHasExternalFacilities(HotelInfo.FacilityServiceInfo facilityServiceInfo) {
        boolean flag = false;
        if (facilityServiceInfo != null) {
            flag = YesNoEnum.judge(facilityServiceInfo.getParking());
        }
        return flag ? YesNoEnum.YES.getValue() : YesNoEnum.NO.getValue();
    }

    /**
     * 处理酒店来源信息
     *
     * @param hotelId
     * @param hotelSourceInfoDO
     * @param hotelNo
     */
    private void dealHotelSource(String hotelId, HotelSourceInfoDO hotelSourceInfoDO, String hotelNo) {
        if (hotelSourceInfoDO == null) {
            hotelSourceInfoDO = new HotelSourceInfoDO();
            hotelSourceInfoDO.setId(UUIDUtil.randomID());
            hotelSourceInfoDO.setSourceId(sourceId);
            hotelSourceInfoDO.setSourceHotelId(hotelNo);
            hotelSourceInfoDO.setHotelId(hotelId);
            hotelSourceInfoDO.setSourceType(SourceTypeEnum.OTA.getValue());
            hotelSourceInfoDO.setSubSourceType(SubSourceTypeEnum.WYN.getValue());
            hotelSourceInfoDO.createDeFault();
            hotelSourceInfoService.save(hotelSourceInfoDO);
        }
    }

    /**
     * 处理酒店详情
     *
     * @param hotelId
     * @param hotelDetailInfoDO
     * @param summary
     */
    private void dealHotelDetail(String hotelId, HotelDetailInfoDO hotelDetailInfoDO, String summary) {
        if (hotelDetailInfoDO != null) {
            if (!Objects.equals(hotelDetailInfoDO.getDescription(), summary)) {
                hotelDetailInfoDO.setDescription(summary);
                hotelDetailInfoDO.updateDefault();
                hotelDetailInfoService.updateById(hotelDetailInfoDO);
            }
        } else {
            hotelDetailInfoDO = new HotelDetailInfoDO();
            hotelDetailInfoDO.setId(UUIDUtil.randomID());
            hotelDetailInfoDO.setHotelId(hotelId);
            hotelDetailInfoDO.setDescription(summary);
            hotelDetailInfoDO.createDeFault();
            hotelDetailInfoService.save(hotelDetailInfoDO);
        }
    }

    /**
     * 处理设施
     *
     * @param hotelId
     * @param hotelExternalFacilitiesInfoDOList
     * @param facilitiesCategoryEnum
     * @param val
     */
    private void dealFacilityService(String hotelId, List<HotelExternalFacilitiesInfoDO> hotelExternalFacilitiesInfoDOList, FacilitiesCategoryEnum facilitiesCategoryEnum, Integer val) {
        Optional<HotelExternalFacilitiesInfoDO> hotelExternalFacilitiesInfoDOOptional = hotelExternalFacilitiesInfoDOList == null ? Optional.empty() :
                hotelExternalFacilitiesInfoDOList.stream().filter(hotelExternalFacilitiesInfoDO -> facilitiesCategoryEnum.getValue().equals(hotelExternalFacilitiesInfoDO.getCategoryName())).findFirst();
        HotelExternalFacilitiesInfoDO hotelExternalFacilitiesInfoDO = null;
        if (hotelExternalFacilitiesInfoDOOptional.isPresent()) {
            hotelExternalFacilitiesInfoDO = hotelExternalFacilitiesInfoDOOptional.get();
            if (!Objects.equals(hotelExternalFacilitiesInfoDO.getIsProvide(), val)) {
                hotelExternalFacilitiesInfoDO.setIsProvide(val);
                hotelExternalFacilitiesInfoDO.updateDefault();
                hotelExternalFacilitiesInfoService.updateById(hotelExternalFacilitiesInfoDO);
            }
        } else {
            hotelExternalFacilitiesInfoDO = new HotelExternalFacilitiesInfoDO();
            hotelExternalFacilitiesInfoDO.setId(UUIDUtil.randomID());
            hotelExternalFacilitiesInfoDO.setHotelId(hotelId);
            hotelExternalFacilitiesInfoDO.setCategoryName(facilitiesCategoryEnum.getValue());
            hotelExternalFacilitiesInfoDO.setName(facilitiesCategoryEnum.getDesc());
            hotelExternalFacilitiesInfoDO.setIsProvide(val == null ? YesNoEnum.NO.getValue() : val);
            hotelExternalFacilitiesInfoDO.createDeFault();
            hotelExternalFacilitiesInfoService.save(hotelExternalFacilitiesInfoDO);
        }
    }

    /**
     * 处理酒店标签
     * @param hotelId
     * @param hotelTagsInfoDOList
     * @param facilityServiceInfo
     */
    private void dealHotelTags(String hotelId, List<HotelTagsInfoDO> hotelTagsInfoDOList, HotelInfo.FacilityServiceInfo facilityServiceInfo) {
        List<HotelTagsInfoDO> saveHotelTagsInfoDOList = new ArrayList<>();
        dealHotelTag(hotelId, hotelTagsInfoDOList, TagsEnum.airportShuttle, facilityServiceInfo.getAirportShuttle(), saveHotelTagsInfoDOList);
        dealHotelTag(hotelId, hotelTagsInfoDOList, TagsEnum.breakfast, facilityServiceInfo.getBreakfast(), saveHotelTagsInfoDOList);
        dealHotelTag(hotelId, hotelTagsInfoDOList, TagsEnum.businessCenter, facilityServiceInfo.getBusinessCenter(), saveHotelTagsInfoDOList);
        dealHotelTag(hotelId, hotelTagsInfoDOList, TagsEnum.gymnasium, facilityServiceInfo.getGymnasium(), saveHotelTagsInfoDOList);
        dealHotelTag(hotelId, hotelTagsInfoDOList, TagsEnum.ktv, facilityServiceInfo.getKtv(), saveHotelTagsInfoDOList);
        dealHotelTag(hotelId, hotelTagsInfoDOList, TagsEnum.meetingRoom, facilityServiceInfo.getMeetingRoom(), saveHotelTagsInfoDOList);
        dealHotelTag(hotelId, hotelTagsInfoDOList, TagsEnum.musicExperience, facilityServiceInfo.getMusicExperience(), saveHotelTagsInfoDOList);
        dealHotelTag(hotelId, hotelTagsInfoDOList, TagsEnum.swimmingPool, facilityServiceInfo.getSwimmingPool(), saveHotelTagsInfoDOList);
        hotelTagsInfoService.insertBatch(saveHotelTagsInfoDOList);
    }    /**
     * 处理标签
     *
     * @param hotelId
     * @param hotelTagsInfoDOList 原来的
     * @param tagsEnum
     * @param val
     */
    private void dealHotelTag(String hotelId, List<HotelTagsInfoDO> hotelTagsInfoDOList, TagsEnum tagsEnum, Integer val, List<HotelTagsInfoDO> saveHotelTagsInfoDOList) {
        Optional<HotelTagsInfoDO> hotelTagsInfoDOOptional = hotelTagsInfoDOList == null ? Optional.empty() : hotelTagsInfoDOList.stream().filter(hotelTagsInfoDO -> tagsEnum.getValue().equals(hotelTagsInfoDO.getCode())).findFirst();
        HotelTagsInfoDO hotelTagsInfoDO = null;
        if (hotelTagsInfoDOOptional.isPresent()) {
            hotelTagsInfoDO = hotelTagsInfoDOOptional.get();
            if (Objects.equals(hotelTagsInfoDO.getIsDeleted(), val)) {
                hotelTagsInfoDO.setIsDeleted(YesNoEnum.judge(val) ? YesNoEnum.NO.getValue() : YesNoEnum.YES.getValue());
                hotelTagsInfoDO.updateDefault();
                hotelTagsInfoService.updateById(hotelTagsInfoDO);
            }
        } else {
            if (YesNoEnum.judge(val)) {
                hotelTagsInfoDO = new HotelTagsInfoDO();
                hotelTagsInfoDO.setId(UUIDUtil.randomID());
                hotelTagsInfoDO.setHotelId(hotelId);
                hotelTagsInfoDO.setType(tagsEnum.getTagsTypeEnum().getValue());
                hotelTagsInfoDO.setCode(tagsEnum.getValue());
                hotelTagsInfoDO.createDeFault();
                saveHotelTagsInfoDOList.add(hotelTagsInfoDO);
            }
        }
    }

    /**
     * 处理酒店图片
     * @param hotelId
     * @param hotelInfo
     * @param hotelPicturesInfoDOList
     */
    private void dealHotelPictures(String hotelId, HotelInfo hotelInfo, List<HotelPicturesInfoDO> hotelPicturesInfoDOList) {
        HotelGetPicListParam picListParam = new HotelGetPicListParam();
        picListParam.setHotelCode(hotelInfo.getHotelNo());
        List<HotelPicInfo> hotelPicInfoList = hotelAdapter.getPicList(picListParam);
        List<HotelPicturesInfoDO> saveHotelPicturesInfoDOList = new ArrayList<>();
        List<String> existList = new ArrayList<>();

        hotelPicInfoList.stream().forEach(hotelPicInfo -> {
            Optional<HotelPicturesInfoDO> hotelPicturesInfoDOOptional = hotelPicturesInfoDOList == null ? Optional.empty() : hotelPicturesInfoDOList.stream()
                    .filter(hotelPicturesInfoDO -> Objects.equals(hotelPicInfo.getBigImageUrl(), hotelPicturesInfoDO.getUrl())).findFirst();
            if (!hotelPicturesInfoDOOptional.isPresent()) {
                HotelPicturesInfoDO hotelPicturesInfoDO = new HotelPicturesInfoDO();
                hotelPicturesInfoDO.setId(UUIDUtil.randomID());
                hotelPicturesInfoDO.setHotelId(hotelId);
                hotelPicturesInfoDO.setType(PictureTypeEnum.getPictureTypeEnum(hotelPicInfo.getCategory()).getValue());
                hotelPicturesInfoDO.setUrl(hotelPicInfo.getBigImageUrl());
                hotelPicturesInfoDO.createDeFault();
                saveHotelPicturesInfoDOList.add(hotelPicturesInfoDO);
            }else {
                existList.add(hotelPicturesInfoDOOptional.get().getId());
            }
        });
        hotelPicturesInfoService.insertBatch(saveHotelPicturesInfoDOList);
        //删除图片
        if(hotelPicturesInfoDOList != null) {
            for(HotelPicturesInfoDO hotelPicturesInfoDO : hotelPicturesInfoDOList) {
                if(!existList.contains(hotelPicturesInfoDO.getId())){
                    hotelPicturesInfoDO.setIsDeleted(YesNoEnum.YES.getValue());
                    hotelPicturesInfoDO.updateDefault();
                    hotelPicturesInfoService.updateById(hotelPicturesInfoDO);
                }
            }
        }
    }

    /**
     * 处理物理房型
     *
     * @param hotelId
     * @param hotelInfo
     * @param roomTypeInfo
     * @param sourceRoomTypeIdToRoomTypeIdMap
     * @param roomTypeInfoDOMap
     * @return
     */
    private String dealRoomTypeInfo(String hotelId, HotelInfo hotelInfo, RoomTypeInfo roomTypeInfo, HotelInfo.FacilityServiceInfo facilityServiceInfo, Map<String, String> sourceRoomTypeIdToRoomTypeIdMap, Map<String, RoomTypeInfoDO> roomTypeInfoDOMap, List<RoomTypeInfoDO> saveRoomTypeInfoDOList) {
        String roomTypeId = sourceRoomTypeIdToRoomTypeIdMap.get(hotelInfo.getHotelNo() + roomTypeInfo.getRoomTypeCode());
        RoomTypeInfoDO roomTypeInfoDO = null;
        if (StringUtil.notEmpty(roomTypeId)) {
            roomTypeInfoDO = roomTypeInfoDOMap.get(roomTypeId);
            roomTypeInfoDO.updateDefault();
        } else {
            roomTypeInfoDO = new RoomTypeInfoDO();
            String uuId = UUIDUtil.randomUUID();
            roomTypeInfoDO.setId(UUIDUtil.randomID());
            roomTypeInfoDO.setHotelId(hotelId);
            roomTypeInfoDO.setRoomTypeCode(uuId);
            roomTypeInfoDO.createDeFault();
        }

        roomTypeInfoDO.setRoomTypeName(roomTypeInfo.getRoomTypeName());
        RoomTypeInfo.RoomExtendInfo roomExtendInfo = roomTypeInfo.getRoomExtendInfo();
        if (roomExtendInfo != null) {
            roomTypeInfoDO.setRoomBedInfos(roomExtendInfo.getBedInfo());
            roomTypeInfoDO.setMaxOccupancy(dealAccommodateInfo(roomExtendInfo.getAccommodateInfo()));
            roomTypeInfoDO.setAreaRange(roomExtendInfo.getRoomAreaInfo());
            roomTypeInfoDO.setFloorRange(dealFloorInfo(roomExtendInfo.getFloorInfo()));
            roomTypeInfoDO.setHasWindow(Objects.equals(roomExtendInfo.getWindowInfo(), "Y") ? YesNoEnum.YES.getValue() : YesNoEnum.NO.getValue());
            roomTypeInfoDO.setExtraBedFee(Objects.equals(roomExtendInfo.getAddBed(), "Y") ? BigDecimal.ZERO : null);

        }
        roomTypeInfoDO.setBroadnetType(BroadnetEnum.NO.getValue());
        roomTypeInfoDO.setBathRoomType(1);
        roomTypeInfoDO.setIsSelling(YesNoEnum.YES.getValue());
        if (StringUtil.notEmpty(roomTypeId)) {
            roomTypeInfoService.updateById(roomTypeInfoDO);
        } else {
            saveRoomTypeInfoDOList.add(roomTypeInfoDO);
        }
        return roomTypeInfoDO.getId();
    }

    private Integer dealAccommodateInfo(String accommodateInfo){
        try{
            return Integer.valueOf(accommodateInfo);
        }catch (Exception e){
            log.error("解析房型最大入住人数错误", e);
        }
        return 1;
    }

    private String dealFloorInfo(String floorInfo) {
        if(StringUtil.notEmpty(floorInfo)){
            if(!floorInfo.endsWith("F")){
                floorInfo += "F";
            }
        }
        return floorInfo;
    }
    /**
     * 处理物理酒店来源
     *
     * @param hotelId
     * @param roomTypeId
     * @param hotelInfo
     * @param roomTypeInfo
     * @param sourceRoomTypeIdToRoomTypeIdMap
     */
    private void dealRoomTypeSource(String hotelId, String roomTypeId, HotelInfo hotelInfo, RoomTypeInfo roomTypeInfo, Map<String, String> sourceRoomTypeIdToRoomTypeIdMap, List<RoomTypeSourceInfoDO> saveRoomTypeSourceInfoDOList) {
        if (sourceRoomTypeIdToRoomTypeIdMap.get(hotelInfo.getHotelNo() + roomTypeInfo.getRoomTypeCode()) == null) {
            RoomTypeSourceInfoDO roomTypeSourceInfoDO = new RoomTypeSourceInfoDO();
            roomTypeSourceInfoDO.setId(UUIDUtil.randomID());
            roomTypeSourceInfoDO.setSourceId(sourceId);
            roomTypeSourceInfoDO.setSourceHotelId(hotelInfo.getHotelNo());
            roomTypeSourceInfoDO.setSourceRoomTypeId(roomTypeInfo.getRoomTypeCode());
            roomTypeSourceInfoDO.setHotelId(hotelId);
            roomTypeSourceInfoDO.setRoomTypeId(roomTypeId);
            roomTypeSourceInfoDO.setSourceType(SourceTypeEnum.OTA.getValue());
            roomTypeSourceInfoDO.setSubSourceType(SubSourceTypeEnum.WYN.getValue());
            roomTypeSourceInfoDO.createDeFault();
            saveRoomTypeSourceInfoDOList.add(roomTypeSourceInfoDO);
        }
    }

    /**
     * 处理房型图片
     * @param roomTypeId
     * @param roomTypePicInfoList
     * @param roomTypePicturesInfoDOList
     * @param saveRoomTypePicturesInfoDOList
     */
    private void dealRoomTypePictures(String roomTypeId, List<RoomTypePicInfo> roomTypePicInfoList, List<RoomTypePicturesInfoDO> roomTypePicturesInfoDOList, List<RoomTypePicturesInfoDO> saveRoomTypePicturesInfoDOList) {
        if (roomTypePicInfoList == null) {
            return;
        }

        List<String> existList = new ArrayList<>();
        roomTypePicInfoList.stream().forEach(roomTypePicInfo -> {
            Optional<RoomTypePicturesInfoDO> roomTypePicturesInfoDOOptional = roomTypePicturesInfoDOList == null ? Optional.empty() : roomTypePicturesInfoDOList.stream()
                    .filter(roomTypePicturesInfoDO -> Objects.equals(roomTypePicInfo.getBigImageUrl(), roomTypePicturesInfoDO.getUrl())).findFirst();
            if (!roomTypePicturesInfoDOOptional.isPresent()) {
                RoomTypePicturesInfoDO roomTypePicturesInfoDO = new RoomTypePicturesInfoDO();
                roomTypePicturesInfoDO.setId(UUIDUtil.randomID());
                roomTypePicturesInfoDO.setRoomTypeId(roomTypeId);
                roomTypePicturesInfoDO.setType(PictureTypeEnum.ROOM.getValue());
                roomTypePicturesInfoDO.setUrl(roomTypePicInfo.getBigImageUrl());
                roomTypePicturesInfoDO.createDeFault();
                saveRoomTypePicturesInfoDOList.add(roomTypePicturesInfoDO);
            }else {
                existList.add(roomTypePicturesInfoDOOptional.get().getId());
            }
        });
        if(roomTypePicturesInfoDOList != null) {
            for(RoomTypePicturesInfoDO roomTypePicturesInfoDO : roomTypePicturesInfoDOList){
                if(!existList.contains(roomTypePicturesInfoDO.getId())){
                    roomTypePicturesInfoDO.setIsDeleted(YesNoEnum.YES.getValue());
                    roomTypePicturesInfoDO.updateDefault();
                    roomTypePicturesInfoService.updateById(roomTypePicturesInfoDO);
                }
            }
        }

    }

//    private void dealRoomTypeTag(String roomTypeId, List<RoomTypeTagsInfoDO> roomTypeTagsInfoDOList, TagsEnum tagsEnum, Integer val) {
//        Optional<RoomTypeTagsInfoDO> roomTypeTagsInfoDOOptional = roomTypeTagsInfoDOList == null ? Optional.empty() : roomTypeTagsInfoDOList.stream().filter(roomTypeTagsInfoDO -> tagsEnum.getValue().equals(roomTypeTagsInfoDO.getCode())).findFirst();
//        RoomTypeTagsInfoDO roomTypeTagsInfoDO = null;
//        if (roomTypeTagsInfoDOOptional.isPresent()) {
//            roomTypeTagsInfoDO = roomTypeTagsInfoDOOptional.get();
//            if (Objects.equals(roomTypeTagsInfoDO.getIsDeleted(), val)) {
//                roomTypeTagsInfoDO.setIsDeleted(YesNoEnum.judge(val) ? YesNoEnum.NO.getValue() : YesNoEnum.YES.getValue());
//                roomTypeTagsInfoDO.updateDefault();
//                roomTypeTagsInfoService.updateById(roomTypeTagsInfoDO);
//            }
//        } else {
//            if (YesNoEnum.judge(val)) {
//                roomTypeTagsInfoDO = new RoomTypeTagsInfoDO();
//                roomTypeTagsInfoDO.setId(UUIDUtil.randomUUID());
//                roomTypeTagsInfoDO.setRoomTypeId(roomTypeId);
//                roomTypeTagsInfoDO.setType(tagsEnum.getTagsTypeEnum().getValue());
//                roomTypeTagsInfoDO.setCode(tagsEnum.getValue());
//                roomTypeTagsInfoDO.createDeFault();
//                roomTypeTagsInfoService.save(roomTypeTagsInfoDO);
//            }
//        }
//    }

    /**
     * 处理房型
     * @param hotelId
     * @param roomTypeId
     * @param hotelInfo
     * @param roomTypeInfo
     * @param facilityServiceInfo
     * @param roomTypeIdToRoomIdMap
     * @param roomInfoDOMap
     * @param saveRoomInfoDOList
     * @return
     */
    private String dealRoomInfo(String hotelId, String roomTypeId, HotelInfo hotelInfo, RoomTypeInfo roomTypeInfo, HotelInfo.FacilityServiceInfo facilityServiceInfo, Map<String, String> roomTypeIdToRoomIdMap, Map<String, RoomInfoDO> roomInfoDOMap, List<RoomInfoDO> saveRoomInfoDOList) {
        String roomId = roomTypeIdToRoomIdMap.get(roomTypeId);
        RoomInfoDO roomInfoDO = null;
        if (StringUtil.notEmpty(roomId)) {
            roomInfoDO = roomInfoDOMap.get(roomId);
            roomInfoDO.updateDefault();
        } else {
            roomInfoDO = new RoomInfoDO();
            String uuId = UUIDUtil.randomUUID();
            roomInfoDO.setId(UUIDUtil.randomID());
            roomInfoDO.setHotelId(hotelId);
            roomInfoDO.setRoomTypeId(roomTypeId);
            roomInfoDO.setRoomCode(uuId);
            roomInfoDO.createDeFault();
        }
        roomInfoDO.setRoomName(dealRoomName(roomTypeInfo.getRoomTypeName()));
        roomInfoDO.setRoomType("unkown");
        RoomTypeInfo.RoomExtendInfo roomExtendInfo = roomTypeInfo.getRoomExtendInfo();
        if (roomExtendInfo != null) {
            roomInfoDO.setRoomBedInfos(dealRoomBedInfo(roomExtendInfo.getBedInfo()));
            roomInfoDO.setMaxOccupancy(dealAccommodateInfo(roomExtendInfo.getAccommodateInfo()));
            roomInfoDO.setAreaRange(roomExtendInfo.getRoomAreaInfo());
            roomInfoDO.setFloorRange(dealFloorInfo(roomExtendInfo.getFloorInfo()));
            roomInfoDO.setHasWindow(Objects.equals(roomExtendInfo.getWindowInfo(), "Y") ? YesNoEnum.YES.getValue() : YesNoEnum.NO.getValue());
            roomInfoDO.setExtraBedFee(Objects.equals(roomExtendInfo.getAddBed(), "Y") ? BigDecimal.ZERO : null);
        }
        roomInfoDO.setBathRoomType(1);
        roomInfoDO.setBroadnetType(BroadnetEnum.NO.getValue());
        roomInfoDO.setBreakfastType(YesNoEnum.judge(facilityServiceInfo.getBreakfast()) ? "1" : "0");
//        roomInfoDO.setBreakfastType("2");
        roomInfoDO.setAdvanceDay(0);
        roomInfoDO.setAdvanceTime(DateUtil.floorToDate(new Date()));
        roomInfoDO.setStayNights(1);
        roomInfoDO.setIsCanCancel(YesNoEnum.YES.getValue());
        roomInfoDO.setOrderAffirmType(2);
        roomInfoDO.setStatus(roomTypeInfo.getStatus());

        roomInfoDO.setPersonInfoType(1);
        roomInfoDO.setIsNeedName(YesNoEnum.YES.getValue());
        roomInfoDO.setIsNeedPhone(YesNoEnum.YES.getValue());
        roomInfoDO.setIsNeedIdcard(YesNoEnum.NO.getValue());
        roomInfoDO.setIsNeedEmail(YesNoEnum.NO.getValue());
        if (StringUtil.notEmpty(roomId)) {
            roomInfoService.updateById(roomInfoDO);
        } else {
            saveRoomInfoDOList.add(roomInfoDO);
        }
        return roomInfoDO.getId();
    }

    private String dealRoomName(String roomName) {
        if(StringUtil.notEmpty(roomName)){
            return roomName.replace("双拼", "双人").replace("单人房","大床房");
        }
        return roomName;
    }

    private String dealRoomBedInfo(String roomBedInfo){
        if(StringUtil.notEmpty(roomBedInfo)){
            return roomBedInfo.replace("单人床", "大床房");
        }
        return roomBedInfo;
    }

    /**
     * 处理酒店来源
     * @param hotelId
     * @param roomTypeId
     * @param roomId
     * @param hotelInfo
     * @param roomTypeInfo
     * @param roomTypeIdToRoomIdMap
     * @param saveRoomSourceInfoDOList
     */
    private void dealRoomSource(String hotelId, String roomTypeId, String roomId, HotelInfo hotelInfo, RoomTypeInfo roomTypeInfo, Map<String, String> roomTypeIdToRoomIdMap, List<RoomSourceInfoDO> saveRoomSourceInfoDOList) {
        if (roomTypeIdToRoomIdMap.get(roomTypeId) == null) {
            RoomSourceInfoDO roomSourceInfoDO = new RoomSourceInfoDO();
            roomSourceInfoDO.setId(UUIDUtil.randomID());
            roomSourceInfoDO.setSupplierId(sourceId);
            roomSourceInfoDO.setSupplierHotelId(hotelInfo.getHotelNo());
            roomSourceInfoDO.setSupplierRoomTypeId(roomTypeInfo.getRoomTypeCode());
            roomSourceInfoDO.setSupplierRoomId(roomTypeInfo.getRoomTypeCode());

            roomSourceInfoDO.setHotelId(hotelId);
            roomSourceInfoDO.setRoomTypeId(roomTypeId);
            roomSourceInfoDO.setRoomId(roomId);
            roomSourceInfoDO.setSourceType(SourceTypeEnum.OTA.getValue());
            roomSourceInfoDO.setSubSourceType(SubSourceTypeEnum.WYN.getValue());
            roomSourceInfoDO.createDeFault();
            saveRoomSourceInfoDOList.add(roomSourceInfoDO);
        }
    }

    /**
     * 处理取消规则
     * @param roomId
     * @param roomCancelRuleInfoDOListMap
     * @param saveRoomCancelRuleInfoList
     */
    private void dealRoomCancelRuleInfo(String roomId, Map<String, List<RoomCancelRuleInfoDO>> roomCancelRuleInfoDOListMap, List<RoomCancelRuleInfoDO> saveRoomCancelRuleInfoList) {
        List<RoomCancelRuleInfoDO> roomCancelRuleInfoDOList = roomCancelRuleInfoDOListMap.get(roomId);
        if(roomCancelRuleInfoDOList == null || roomCancelRuleInfoDOList.size() == 0) {
            RoomCancelRuleInfoDO roomCancelRuleInfoDO = new RoomCancelRuleInfoDO();
            roomCancelRuleInfoDO.setId(UUIDUtil.randomID());
            roomCancelRuleInfoDO.setRoomId(roomId);
            roomCancelRuleInfoDO.setType(2);
            roomCancelRuleInfoDO.setMinutes(0);
            roomCancelRuleInfoDO.setDay(0);
            roomCancelRuleInfoDO.setHour(18);
            roomCancelRuleInfoDO.createDeFault();
            saveRoomCancelRuleInfoList.add(roomCancelRuleInfoDO);
        }
    }

    /**
     * 处理日态信息
     * @param hotelId
     * @param roomTypeId
     * @param roomId
     * @param hotelInfo
     * @param roomTypeInfo
     * @param roomStatusInfoListMap
     * @param saveRoomDayPriceDOList
     */
    private void dealDayPrice(String hotelId, String roomTypeId, String roomId, HotelInfo hotelInfo, RoomTypeInfo roomTypeInfo,  Map<String, List<RoomStatusInfo>> roomStatusInfoListMap , List<RoomDayPriceDO> saveRoomDayPriceDOList) {
        List<RoomStatusInfo> roomStatusInfoList = roomStatusInfoListMap.get(roomTypeInfo.getRoomTypeCode());
        if(roomStatusInfoList == null) {
            return;
        }
        roomStatusInfoList.stream().forEach(roomStatusInfo -> {
            RoomDayPriceDO roomDayPriceDO = new RoomDayPriceDO();
            roomDayPriceDO.setId(UUIDUtil.randomID());
            roomDayPriceDO.setRoomId(roomId);
            HotelInfo.FacilityServiceInfo facilityServiceInfo = hotelInfo.getFacilityServiceInfo();
            if(facilityServiceInfo != null && YesNoEnum.judge(facilityServiceInfo.getBreakfast())){
                roomDayPriceDO.setBreakfastNum(1);
            }else {
                roomDayPriceDO.setBreakfastNum(0);
            }
            roomDayPriceDO.setLunchNum(0);
            roomDayPriceDO.setDinnerNum(0);
            roomDayPriceDO.setIsOptionalMeal("False");
            List<RoomStatusInfo.RatePlanInfo> ratePlanInfoList = roomStatusInfo.getRatePlanInfoList();
            if(ratePlanInfoList != null && ratePlanInfoList.size() > 0) {
                RoomStatusInfo.RatePlanInfo ratePlanInfo = ratePlanInfoList.get(0);
                dealRatePlanInfo(roomDayPriceDO, ratePlanInfo, roomTypeInfo.getTotalRooms());
                roomDayPriceDO.setIncreaseRate(BigDecimal.ZERO);
                roomDayPriceDO.setIsEnabled(YesNoEnum.YES.getValue());
            }
            roomDayPriceDO.setDate(DateUtil.parse(roomStatusInfo.getBizDay(), DateUtil.DEFAULT_DATE));
            roomDayPriceDO.setWeek(DateUtil.getWeek(roomDayPriceDO.getDate()));
            roomDayPriceDO.setIsSelling(YesNoEnum.YES.getValue());
            roomDayPriceDO.createDeFault();
            saveRoomDayPriceDOList.add(roomDayPriceDO);
        });

    }

    private void dealRatePlanInfo(RoomDayPriceDO roomDayPriceDO, RoomStatusInfo.RatePlanInfo ratePlanInfo, Integer totalRooms) {
        Integer orderNum = ratePlanInfo.getOrderNum();
        BigDecimal disRate = ratePlanInfo.getDisRate();
        roomDayPriceDO.setStock(orderNum);
        roomDayPriceDO.setBookedQuantity(totalRooms - orderNum);
        roomDayPriceDO.setCostPrice(disRate);
        roomDayPriceDO.setSalePrice(disRate);
        roomDayPriceDO.setDistributorPrice(disRate);
    }

    @Override
    public void synRoomDayPrice(Logger log, RoomSourceInfoDO roomSourceInfoDO, String inDate, String outDate) {
        log.info("判断是否更新日态价格信息");
        List<RoomDayPriceDO> updateRoomDayPriceList = new ArrayList<>();
        //维也纳日态价格信息

        List<RoomStatusInfo> roomStatusInfos = null;
        try{
            RoomStatusParam param = new RoomStatusParam();
            param.setHotelNo(roomSourceInfoDO.getSupplierHotelId());
            param.setRoomTypeNo(roomSourceInfoDO.getSupplierRoomId());
            param.setInDate(inDate);
            param.setOutDate(outDate);
            param.setRatePlanList(RatePlanEnum.DisRate.getValue());
            roomStatusInfos = roomAdapter.roomStatus(param);
        }catch (Exception e) {
            log.error("查询日态信息错误", e);
            throw e;
        }
        //数据库日态价格信息
        List<RoomDayPriceDO> roomDayPriceDOList  = roomDayPriceService.list(new QueryWrapper<RoomDayPriceDO>().lambda().eq(RoomDayPriceDO::getRoomId, roomSourceInfoDO.getRoomId())
                .and(t -> t.between(RoomDayPriceDO::getDate, inDate, DateUtil.format(DateUtil.addDay(DateUtil.parse(outDate, DateUtil.DEFAULT_DATE), -1), DateUtil.DEFAULT_DATE))));
        //比较
        for (RoomDayPriceDO roomDayPriceDO : roomDayPriceDOList) {
            Optional<RoomStatusInfo> roomStatusInfoOptional = roomStatusInfos.stream().filter(roomStatusInfo -> roomStatusInfo.getBizDay().equals(DateUtil.format(roomDayPriceDO.getDate(), DateUtil.DEFAULT_DATE))).findFirst();
            if (roomStatusInfoOptional.isPresent()) {
                RoomStatusInfo roomStatusInfo = roomStatusInfoOptional.get();
                RoomStatusInfo.RatePlanInfo ratePlanInfo = roomStatusInfo.getRatePlanInfoList().get(0);
                if ((!ratePlanInfo.getOrderNum().equals(roomDayPriceDO.getStock()))
                        || (ratePlanInfo.getDisRate().setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(roomDayPriceDO.getCostPrice().setScale(2, BigDecimal.ROUND_HALF_UP)) != 0)) {
                    //更新
                    dealRatePlanInfo(roomDayPriceDO, ratePlanInfo, roomDayPriceDO.getStock() + roomDayPriceDO.getBookedQuantity());
                    updateRoomDayPriceList.add(roomDayPriceDO);
                }
            }
        }
        if(updateRoomDayPriceList.size() > 0) {
            StopWatch stopWatch = new StopWatch();
            Map<String, String> failMap = new HashMap<>();
            SyncRecordDO syncRecordDO = new SyncRecordDO();
            syncRecordDO.setDateStart(new Date());
            TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
            try{
                roomDayPriceService.updateBatchById(updateRoomDayPriceList);
                transactionManager.commit(transactionStatus);
            } catch (Exception e) {
                String errorLog = "更新日态信息失败，roomId = " + roomSourceInfoDO.getRoomId();
                log.error(errorLog, e);
                failMap.put(roomSourceInfoDO.getRoomId(), errorLog + e.getMessage());
                try{
                    transactionManager.rollback(transactionStatus);
                }catch(Exception e1) {
                    log.error(e1.getMessage(), e1);
                }
            }
            syncRecordDO.setDateEnd(new Date());
            if(failMap.size() > 0) {
                syncRecordDO.setResult(SyncResultEnum.FAIL.getValue());
                syncRecordDO.setRemark(JacksonUtils.objectToJson(objectMapper, failMap));
            }else {
                syncRecordDO.setResult(SyncResultEnum.SUCCESS.getValue());
            }
            syncRecordService.save(syncRecordDO);
            log.info("更新日态信息完成，耗时{}ms", stopWatch.elapsedTime());
        }



    }
}