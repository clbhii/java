package com.cl.wyn.core.controller;

import com.cl.wyn.core.util.common.ListUtil;
import com.cl.wyn.core.util.common.StopWatch;
import com.cl.wyn.core.util.json.JacksonBuilder;
import com.cl.wyn.core.util.json.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * by cl at 2020/7/6 0006
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {

    private ObjectMapper objectMapper = new JacksonBuilder().build();

    @Pointcut("execution(* com.cl.wyn.core.controller..*Controller.*(..))")
    public void controllerAspect() {
    }

    @Pointcut("execution(* com.cl.wyn.core.adapter.impl.OrderAdapterImpl.*(..))")
    public void OrderAdapterImpl() {
    }


    @Around(value = "controllerAspect() || OrderAdapterImpl()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        List<Object> objectList = ListUtil.removeInterfaceType(pjp.getArgs(), BindingResult.class);
        log.info("请求:{},参数:{}", pjp.getSignature(), JacksonUtils.objectToJson(objectMapper, objectList));
        StopWatch stopWatch = new StopWatch();
        Object response = pjp.proceed();
        log.info("响应:{}, 耗时：{}", JacksonUtils.objectToJson(objectMapper, response), stopWatch.elapsedTime());
        return response;
    }
    @JsonIgnoreType
    @interface IgnoreAvroSchemaField {
    }

}
