package com.cl.wyn.core.scheduling;

import com.cl.wyn.core.service.ISynchronousDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * by cl at 2020/8/5 0005
 */
@Component
@Slf4j
public class SyncDataTask {
//    @Value("${scheduling.enable}")
//    private boolean enable;
//    @Autowired
//    private ISynchronousDataService synchronousDataService;
//    @Scheduled(cron = "${scheduling.syncData.cron}")
//    public void work() {
//        // task execution logic
//        if(enable){
//            synchronousDataService.synAll();
//        }else {
//            log.info("未开启执行SyncDataTask");
//        }
//
//    }
}