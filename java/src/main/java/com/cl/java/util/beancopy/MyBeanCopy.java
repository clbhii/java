package com.cl.java.util.beancopy;


public class MyBeanCopy implements BeanCopyFacade<SourceBean, TargetBean> {

    @Override
    public void copyBean(SourceBean sourceBean, TargetBean targetBean) throws Exception {
        long start = System.nanoTime();
        targetBean = BeanUtils.copyProperties(sourceBean, targetBean.getClass());
        long end = System.nanoTime();

        System.out.println(String.format("%s consume %d microsecond", "apache  copy property", (end - start) / 1000000));
    }
}
