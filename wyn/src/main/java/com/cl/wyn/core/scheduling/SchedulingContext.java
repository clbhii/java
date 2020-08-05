package com.cl.wyn.core.scheduling;

import com.cl.wyn.core.common.BizException;
import com.cl.wyn.core.common.ErrorCode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * by cl at 2020/8/5 0005
 */
@Component
public class SchedulingContext {
    /**
     * 任务
     */
    private Map<String, Runnable> runnableMap = new HashMap<>();
    /**
     * 执行Future
     */
    private Map<String, ScheduledFuture> scheduledFutureMap = new HashMap<>();

    public void setRunnable(String taskId, Runnable runnable) {
        runnableMap.put(taskId, runnable);
    }

    public Runnable getRunnable(String taskId) {
        Runnable runnable = runnableMap.get(taskId);
        if(runnable == null) {
            throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR, "当前任务不存在");
        }
        return runnable;
    }

    public void setScheduledFuture(String taskId, ScheduledFuture scheduledFuture) {
        scheduledFutureMap.put(taskId, scheduledFuture);
    }

    public ScheduledFuture getScheduledFuture(String taskId) {
        return scheduledFutureMap.get(taskId);
    }

}
