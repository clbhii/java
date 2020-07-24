package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.adapter.info.param.BaseParam;
import com.cl.wyn.core.common.Contants;
import com.cl.wyn.core.entity.DistrictInfoDO;
import com.cl.wyn.core.mapper.DistrictInfoMapper;
import com.cl.wyn.core.service.IDistrictInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 区域信息 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-06-28
 */
@Service
public class DistrictInfoServiceImpl extends ServiceImpl<DistrictInfoMapper, DistrictInfoDO> implements IDistrictInfoService {

    @Override
    public void insertBatch(List<DistrictInfoDO> districtInfoDOList) {
        int size = districtInfoDOList.size();
        if(size == 0) {
            return;
        }
        int page = size/ Contants.PAGE_SIZE;
        for(int i = 0; i <= page; i++) {
            if(i == page) {
                baseMapper.insertBatch(districtInfoDOList.subList(i*Contants.PAGE_SIZE, size-i*Contants.PAGE_SIZE));
            }else {
                baseMapper.insertBatch(districtInfoDOList.subList(i*Contants.PAGE_SIZE, page*Contants.PAGE_SIZE));
            }
        }
    }
}
