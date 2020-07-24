package com.cl.wyn.core.adapter;

import com.cl.wyn.core.WynApplication;
import com.cl.wyn.core.adapter.ICityAdapter;
import com.cl.wyn.core.adapter.info.CityInfo;
import com.cl.wyn.core.adapter.info.ProvinceCityInfo;
import com.cl.wyn.core.adapter.info.param.BaseParam;
import com.cl.wyn.core.util.json.JacksonBuilder;
import com.cl.wyn.core.util.json.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * by cl at 2020/6/25 0025
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WynApplication.class)
@Slf4j
public class CityAdapterTest {
    @Autowired
    private ICityAdapter cityAdapter;

    private ObjectMapper objectMapper = new JacksonBuilder().setSerializationInclusion(JsonInclude.Include.NON_NULL).build();
    @Test
    public void getList() {
        BaseParam baseParam = new BaseParam();
        List<CityInfo> list = cityAdapter.getList(baseParam);
        log.info(JacksonUtils.objectToJson(objectMapper, list));
    }
    @Test
    public void getProvinceCityList() {
        BaseParam baseParam = new BaseParam();
        List<ProvinceCityInfo> list = cityAdapter.getProvinceCityList(baseParam);
        log.info(JacksonUtils.objectToJson(objectMapper, list));
    }
}


