package com.cl.wyn.core.scheduling;

import com.cl.wyn.core.service.ISynchronousDataService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

/**
 * by cl at 2020/8/5 0005
 */
@Component
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

    @Override
    public void run() {
        synchronousDataService.synAll();
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
