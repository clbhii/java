package com.cl.wyn.core.adapter.info.param;

import com.cl.wyn.core.adapter.info.param.BaseParam;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * by cl at 2020/6/24 0024
 */
@Data
public class ChannelAuthParam extends BaseParam {
    /**
     * 渠道key
     */
    @JsonProperty("api_key")
    private String apiKey;
    /**
     * 渠道secret
     */
    @JsonProperty("api_secret")
    private String apiSecret;
}
