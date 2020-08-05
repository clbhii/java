package com.cl.wyn.core.controller;

import com.cl.wyn.core.common.BizException;
import com.cl.wyn.core.common.ErrorCode;
import com.cl.wyn.core.common.Result;
import com.cl.wyn.core.common.ResultSupport;
import com.cl.wyn.core.scheduling.SchedulingContext;
import com.cl.wyn.core.service.ISynchronousDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * by cl at 2020/8/5 0005
 */
@Api(tags = "定时任务接口")
@RequestMapping("taskScheduler")
@RestController
public class TaskSchedulerController {
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private SchedulingContext schedulingContext;

    @ApiOperation(value = "触发任务", httpMethod = "POST")
    @RequestMapping(value = "/scheduleTask", method = RequestMethod.POST)
    public Result scheduleTask(@ApiParam(defaultValue = "syncData", example = "syncData:同步数据任务id") @RequestParam(required = true) String taskId, String cron) {
        if(schedulingContext.getScheduledFuture(taskId) != null) {
            throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR, "当前任务已触发，不能重新触发");
        }
        Runnable runnable = schedulingContext.getRunnable(taskId);
        ScheduledFuture<?> schedule = taskScheduler.schedule(runnable, new CronTrigger(cron));
        schedulingContext.setScheduledFuture(taskId, schedule);
        return new ResultSupport(true, ErrorCode.SUCCESS);
    }

    @ApiOperation(value = "取消任务", httpMethod = "POST")
    @RequestMapping(value = "/cancelTask", method = RequestMethod.POST)
    public Result cancelTask(@ApiParam(defaultValue = "syncData", example = "syncData:同步数据任务id") @RequestParam(required = true) String taskId) {
        ScheduledFuture scheduledFuture = schedulingContext.getScheduledFuture(taskId);
        if(scheduledFuture == null) {
            throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR, "当前任务未触发，不能取消");
        }
        scheduledFuture.cancel(false);
        schedulingContext.setScheduledFuture(taskId, null);
        return new ResultSupport(true, ErrorCode.SUCCESS);
    }
}
