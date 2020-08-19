package com.cl.wyn.core.scheduling;

import com.cl.wyn.core.service.ISynchronousDataService;
import com.cl.wyn.core.util.common.DateUtil;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * by cl at 2020/8/5 0005
 */
@Component
@Slf4j
public class SyncDataRunnable  implements InitializingBean, Runnable {
    /**
     * 任务id
     */
    private String taskId = "syncData";
    @Autowired
    private SchedulingContext schedulingContext;
    @Autowired
    private ISynchronousDataService synchronousDataService;
    @Autowired
    private TaskScheduler taskScheduler;
    @Value("${scheduling.enable}")
    private boolean enable;
    @Value("${scheduling.syncData.cron}")
    private String cron;

    private Map<Integer, Integer> timeHashMap = new HashMap<>();

    {
        timeHashMap.put(0, 30);
        timeHashMap.put(2, 3);
        timeHashMap.put(4, 7);
        timeHashMap.put(6, 3);
        timeHashMap.put(8, 15);
        timeHashMap.put(10, 3);
        timeHashMap.put(12, 7);
        timeHashMap.put(14, 3);
        timeHashMap.put(16, 15);
        timeHashMap.put(18, 3);
        timeHashMap.put(20, 7);
        timeHashMap.put(22, 3);

    }
    @Override
    public void run() {
        Integer hour  = DateUtil.getHour(new Date());
        Integer day = timeHashMap.get(hour);
        log.info("维也纳同步任务开始;同步天数：" + day);
        if(day != null) {
            synchronousDataService.synAll(day);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        schedulingContext.setRunnable(taskId, this);
        if(enable){
            Runnable runnable = schedulingContext.getRunnable(taskId);
            ScheduledFuture<?> schedule = taskScheduler.schedule(runnable, new CronTrigger(cron));
            schedulingContext.setScheduledFuture(taskId, schedule);
        }
    }
}