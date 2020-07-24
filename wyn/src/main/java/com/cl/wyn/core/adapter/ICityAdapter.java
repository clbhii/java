package com.cl.wyn.core.adapter;

import com.cl.wyn.core.adapter.info.CityInfo;
import com.cl.wyn.core.adapter.info.ProvinceCityInfo;
import com.cl.wyn.core.adapter.info.param.BaseParam;

import java.util.List;

/**
 * by cl at 2020/6/25 0025
 */
public interface ICityAdapter {

    /**
     * 获取城市列表(包括区域、商圈信息)
     * @param param
     * @return
     */
    List<CityInfo> getList(BaseParam param);

    /**
     *获取省份城市列表
     * @param param
     * @return
     */
    List<ProvinceCityInfo> getProvinceCityList(BaseParam param);
}
