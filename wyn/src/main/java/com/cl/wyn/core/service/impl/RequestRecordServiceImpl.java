package com.cl.wyn.core.service.impl;

import com.cl.wyn.core.entity.RequestRecordDO;
import com.cl.wyn.core.mapper.RequestRecordMapper;
import com.cl.wyn.core.service.IRequestRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 请求记录表 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-07-02
 */
@Service
public class RequestRecordServiceImpl extends ServiceImpl<RequestRecordMapper, RequestRecordDO> implements IRequestRecordService {

    @Override
    public void saveRequestRecordDO(String path, String param, String result, Long costTime) {
        RequestRecordDO requestRecordDO = new RequestRecordDO();
        requestRecordDO.setPath(path);
        requestRecordDO.setParam(param);
        requestRecordDO.setResult(result);
        requestRecordDO.setCostTime(costTime);
        requestRecordDO.setRequestTime(new Date());
        baseMapper.insert(requestRecordDO);
    }
}
