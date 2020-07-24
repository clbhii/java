package com.cl.wyn.core.adapter.impl;

import com.cl.wyn.core.adapter.IChannelAdapter;
import com.cl.wyn.core.adapter.info.BaseResult;
import com.cl.wyn.core.adapter.info.param.ChannelAuthParam;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * by cl at 2020/6/24 0024
 */
@Component
public class ChannelAdapterImpl extends AbsAdapter implements IChannelAdapter {

    @Value("${wyn.api.channel.authPath}")
    private String authPath;
    @Value("${wyn.apiKey}")
    private String apiKey;
    @Value("${wyn.apiSecret}")
    private String apiSecret;
    @Override
    public String auth() {
        ChannelAuthParam param = new ChannelAuthParam();
        param.setApiKey(apiKey);
        param.setApiSecret(apiSecret);
        return post(authPath, param, new TypeReference<BaseResult<String>>() {});
    }
}
