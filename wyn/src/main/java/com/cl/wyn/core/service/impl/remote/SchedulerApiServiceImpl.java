package com.cl.wyn.core.service.impl.remote;

import com.cl.wyn.core.service.remote.ISchedulerApiService;
import com.cl.wyn.core.util.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * by cl at 2020/8/12 0012
 */
@Component
@Slf4j
public class SchedulerApiServiceImpl implements ISchedulerApiService {
    @Value("${scheduler.baseUrl}")
    private String BaseUrl;
    @Value("${scheduler.syncFliggyHotelInfoPath}")
    private String syncFliggyHotelInfoPath;

    @Override
    public void syncFliggyHotelInfo(Map<String, String> map) {
        try{
            HttpUtil.get(BaseUrl + syncFliggyHotelInfoPath, null, map);
        }catch (Exception e){
            log.error("同步完成通知", e);
        }
    }
}
