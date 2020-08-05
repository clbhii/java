package com.cl.wyn.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

/**
 * by cl at 2020/8/5 0005
 */
@Configuration
public class SchedulingConfig {

    @Bean
    public TaskScheduler getTaskScheduler(){
        return new ConcurrentTaskScheduler();
    }
}
