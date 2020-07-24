package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.DistrictInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 区域信息 服务类
 * </p>
 *
 * @author cl
 * @since 2020-06-28
 */
public interface IDistrictInfoService extends IService<DistrictInfoDO> {

    void insertBatch(List<DistrictInfoDO> districtInfoDOList);

}
