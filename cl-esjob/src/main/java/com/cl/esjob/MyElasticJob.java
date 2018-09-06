package com.cl.esjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperRegistryCenter;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cl 2018年8月27日
 *
 */
@Slf4j
public class MyElasticJob implements SimpleJob {
    
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0: 
                // do something by sharding item 0
            	log.info("do something by sharding item 0");
                break;
            case 1: 
                // do something by sharding item 1
            	log.info("do something by sharding item 1");
                break;
            case 2: 
                // do something by sharding item 2
            	log.info("do something by sharding item 2");
                break;
            // case n: ...
        }
    }
    
    public static void main(String[] args) throws Exception {
    	new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
    	Thread.sleep(1000000);
    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("127.0.0.1:2181", "elastic-job-demo"));
        regCenter.init();
        return regCenter;
    }
    
    private static LiteJobConfiguration createJobConfiguration() {
        // 创建作业配置
//    	// 定义作业核心配置
      JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("myElasticJob", "0/15 * * * * ?", 3).build();
      // 定义SIMPLE类型配置
      SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, MyElasticJob.class.getCanonicalName());
      // 定义Lite作业根配置
      LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
      return simpleJobRootConfig;	
    }
}
