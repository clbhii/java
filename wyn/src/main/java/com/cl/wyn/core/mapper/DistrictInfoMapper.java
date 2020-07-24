package com.cl.wyn.core.mapper;

import com.cl.wyn.core.entity.DistrictInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 区域信息 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2020-06-28
 */
public interface DistrictInfoMapper extends BaseMapper<DistrictInfoDO> {

    void insertBatch(List<DistrictInfoDO> districtInfoDOList);

}
