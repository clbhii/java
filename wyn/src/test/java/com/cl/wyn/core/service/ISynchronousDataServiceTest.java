package com.cl.wyn.core.service;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class ISynchronousDataServiceTest {
    @Autowired
    private ISynchronousDataService synchronousDataService;
    @Test
    public void synDistrictInfo() {
        synchronousDataService.synDistrictInfo();
    }
    @Test
    public void synHotel(){
//        synchronousDataService.synHotel(null);
//        synchronousDataService.synHotel("JD7 27927744997883904");
    }
}
