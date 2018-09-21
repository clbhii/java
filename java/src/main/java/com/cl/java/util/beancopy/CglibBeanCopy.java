package com.cl.java.util.beancopy;

import net.sf.cglib.beans.BeanCopier;

public class CglibBeanCopy  implements BeanCopyFacade<SourceBean, TargetBean> {

    private BeanCopier beanCopier = null;

    @Override
    public void copyBean(SourceBean sourceBean, TargetBean targetBean) throws Exception {
        long start = System.nanoTime();
        beanCopier = BeanCopier.create(SourceBean.class, TargetBean.class, false);
        beanCopier.copy(sourceBean, targetBean, null);
        long end = System.nanoTime();

        System.out.println(String.format("%s consume %d microsecond", "cglib BeanCopier", (end - start) / 1000000));
    }
}
