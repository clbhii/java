package com.cl.wyn.core.scheduling;

import com.cl.wyn.core.service.ISynchronousDataService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void run() {
        synchronousDataService.synAll();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        schedulingContext.setRunnable(taskId, this);
    }
}
