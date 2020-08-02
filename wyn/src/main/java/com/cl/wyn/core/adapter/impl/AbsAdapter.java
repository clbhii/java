package com.cl.wyn.core.adapter.impl;

import com.cl.wyn.core.adapter.info.BaseResult;
import com.cl.wyn.core.adapter.info.param.BaseParam;
import com.cl.wyn.core.common.BizException;
import com.cl.wyn.core.common.ErrorCode;
import com.cl.wyn.core.common.WynErrorCode;
import com.cl.wyn.core.service.IRequestRecordService;
import com.cl.wyn.core.util.common.StopWatch;
import com.cl.wyn.core.util.http.HttpUtil;
import com.cl.wyn.core.util.json.JacksonBuilder;
import com.cl.wyn.core.util.json.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * by cl at 2020/6/24 0024
 */
@Slf4j
public abstract class AbsAdapter {
    @Value("${wyn.sid}")
    String sid;
    @Value("${wyn.api.baseUrl}")
    String baseUrl;
    @Autowired
    IRequestRecordService requestRecordService;
    ObjectMapper mapper = new JacksonBuilder().setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true).build();

    <U,T> T post(String path, BaseParam param, TypeReference<U> typeReference) {
        StopWatch stopWatch = new StopWatch();
        param.setSid(sid);
        Map<String, String> dataMap = mapper.convertValue(param, Map.class);
        String res = null;
        try{
            res = HttpUtil.post(baseUrl + path, new HashMap<>(), dataMap);
        }catch(Exception e) {
            res = e.getMessage();
            throw new BizException(ErrorCode.INTERNAL_SERVER_ERROR, e);
        } finally {
            requestRecordService.saveRequestRecordDO(path, JacksonUtils.objectToJson(mapper, dataMap), res, stopWatch.elapsedTime());
        }
        BaseResult<T> BaseResult = (BaseResult<T>) JacksonUtils.jsonToObject(mapper, res, typeReference);
        WynErrorCode wynErrorCode = WynErrorCode.getWynErrorCode(BaseResult.getResult());
        if (!WynErrorCode.SUCCESS.equals(wynErrorCode)) {
            throw new BizException(wynErrorCode.getErrorCode(), BaseResult.getMessage());
        }
        return BaseResult.getData();
    }
}