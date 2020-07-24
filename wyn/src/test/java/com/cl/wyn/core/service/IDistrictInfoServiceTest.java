package com.cl.wyn.core.service;

import com.cl.wyn.core.WynApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * by cl at 2020/6/28 0028
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WynApplication.class)
@Slf4j
public class IDistrictInfoServiceTest {
    @Autowired
    private IDistrictInfoService districtInfoService;
    @Test
    public void get() {
        districtInfoService.getById(2);
    }
}
