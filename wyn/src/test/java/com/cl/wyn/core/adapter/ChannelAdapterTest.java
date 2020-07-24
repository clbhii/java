package com.cl.wyn.core.adapter;

import com.cl.wyn.core.WynApplication;
import com.cl.wyn.core.adapter.IChannelAdapter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * by cl at 2020/6/24 0024
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WynApplication.class)
@Slf4j
public class ChannelAdapterTest {
    @Autowired
    private IChannelAdapter channelAdapter;

    @Test
    public void auth() {
        String token = channelAdapter.auth();
        log.info("token=" + token);
    }
}
