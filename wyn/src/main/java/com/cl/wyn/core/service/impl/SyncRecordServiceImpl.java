package com.cl.wyn.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cl.wyn.core.entity.SyncRecordDO;
import com.cl.wyn.core.mapper.SyncRecordMapper;
import com.cl.wyn.core.service.ISyncRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.wyn.core.util.common.StringUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 同步记录表 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-07-03
 */
@Service
public class SyncRecordServiceImpl extends ServiceImpl<SyncRecordMapper, SyncRecordDO> implements ISyncRecordService {

    @Override
    public boolean judgeIsProcessingRecord() {
//        IPage<SyncRecordDO> userPage = new Page<>(1, 1);//参数一是当前页，参数二是每页个数
//        IPage<SyncRecordDO> page = super.page(userPage, new QueryWrapper<SyncRecordDO>().lambda().orderByDesc(SyncRecordDO::getId));
//        if(page.getTotal() > 0) {
//            if(page.getRecords().get(0).getDateEnd() == null){
//                return true;
//            }
//        }
        return false;
    }

}
