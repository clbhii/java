package com.cl.wyn.core.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * by cl at 2020/6/26 0026
 */
@FeignClient("single-provider")
public interface IHelloService {
    @RequestMapping(value = "/hello")
    String hello();

    @RequestMapping(value = "nice")
    String nice() ;
}
