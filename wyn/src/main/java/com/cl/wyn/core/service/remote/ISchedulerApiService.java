package com.cl.wyn.core.service.remote;

import java.util.Map;

public interface ISchedulerApiService {

    /**
     * 同步完成通知
     * @param map
     */
    void syncFliggyHotelInfo(Map<String, String> map);
}
