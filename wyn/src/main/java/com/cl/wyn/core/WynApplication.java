package com.cl.wyn.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@EnableFeignClients
@MapperScan("com.cl.wyn.core.mapper")
@EnableScheduling
@EnableCaching
public class WynApplication {

	public static void main(String[] args) {
		SpringApplication.run(WynApplication.class, args);
	}
}
