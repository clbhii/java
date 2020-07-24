package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.RequestRecordDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 请求记录表 服务类
 * </p>
 *
 * @author cl
 * @since 2020-07-02
 */
public interface IRequestRecordService extends IService<RequestRecordDO> {


    void saveRequestRecordDO(String path, String param, String result, Long costTime);
}
