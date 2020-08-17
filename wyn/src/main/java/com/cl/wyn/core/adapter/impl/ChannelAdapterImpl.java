package com.cl.wyn.core.adapter.impl;

import com.cl.wyn.core.adapter.IChannelAdapter;
import com.cl.wyn.core.adapter.info.BaseResult;
import com.cl.wyn.core.adapter.info.param.ChannelAuthParam;
import com.cl.wyn.core.util.common.StringUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
    @Autowired
    private CacheManager cacheManager;

    @Override
    public String auth() {
        Cache cache = cacheManager.getCache("myCache");
        String token = cache.get("token", String.class);
        if(StringUtil.notEmpty(token)){
            return token;
        }
        synchronized(this){
            token = cache.get("token", String.class);
            if(StringUtil.notEmpty(token)){
                return token;
            }
            ChannelAuthParam param = new ChannelAuthParam();
            param.setApiKey(apiKey);
            param.setApiSecret(apiSecret);
            token = post(authPath, param, new TypeReference<BaseResult<String>>() {});
            cache.put("token", token);
            return token;
        }
    }
}
