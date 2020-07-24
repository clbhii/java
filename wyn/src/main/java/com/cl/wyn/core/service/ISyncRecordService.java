package com.cl.wyn.core.service;

import com.cl.wyn.core.entity.SyncRecordDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 同步记录表 服务类
 * </p>
 *
 * @author cl
 * @since 2020-07-03
 */
public interface ISyncRecordService extends IService<SyncRecordDO> {


    boolean judgeIsProcessingRecord();
}
