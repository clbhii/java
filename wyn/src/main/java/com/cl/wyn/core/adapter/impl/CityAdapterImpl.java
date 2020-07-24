package com.cl.wyn.core.adapter.impl;

import com.cl.wyn.core.adapter.ICityAdapter;
import com.cl.wyn.core.adapter.info.BaseResult;
import com.cl.wyn.core.adapter.info.CityInfo;
import com.cl.wyn.core.adapter.info.ProvinceCityInfo;
import com.cl.wyn.core.adapter.info.param.BaseParam;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * by cl at 2020/6/25 0025
 */
@Component
public class CityAdapterImpl extends AbsAdapter implements ICityAdapter {

    @Value("${wyn.api.city.getListPath}")
    private String getListPath;
    @Value("${wyn.api.city.getProvinceCityListPath}")
    private String getProvinceCityListPath;

    @Override
    public List<CityInfo> getList(BaseParam param) {
        return post(getListPath, param, new TypeReference<BaseResult<List<CityInfo>>>() {});
    }

    @Override
    public List<ProvinceCityInfo> getProvinceCityList(BaseParam param) {
        return post(getProvinceCityListPath, param, new TypeReference<BaseResult<List<ProvinceCityInfo>>>() {});
    }
}
