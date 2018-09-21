package com.cl.java.util.beancopy;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @author cl 2018年9月11日
 *
 */
public class ApacheBeanCopy implements BeanCopyFacade<SourceBean, TargetBean> {

    @Override
    public void copyBean(SourceBean sourceBean, TargetBean targetBean) throws Exception {
        long start = System.nanoTime();
        BeanUtils.copyProperties(targetBean, sourceBean);
        long end = System.nanoTime();

        System.out.println(String.format("%s consume %d microsecond", "apache  copy property", (end - start) / 1000000));
    }
}
