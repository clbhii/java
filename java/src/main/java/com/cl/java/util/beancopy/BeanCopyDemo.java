package com.cl.java.util.beancopy;

import java.util.ArrayList;
import java.util.List;

import com.cl.java.algorithm.perfermance.StopWatch;

/**
 * 
 * @author cl 2018年9月11日
 *https://yq.aliyun.com/articles/392185
 */
public class BeanCopyDemo {
	private static BeanCopyFacade apacheBeanCopy;
    private static BeanCopyFacade cglibBeanCopy;
    private static BeanCopyFacade springBeanCopy;
    
    private static BeanCopyFacade myBeanCopy;
    private static BeanCopyFacade fastJsonBeanCopy;

    static {
        apacheBeanCopy = new ApacheBeanCopy();
        cglibBeanCopy = new CglibBeanCopy();
        springBeanCopy = new SpringBeanCopy();
        myBeanCopy = new MyBeanCopy();
        fastJsonBeanCopy = new FastJsonCopyFacade();
        
    }
    public static void main(String[] args) throws Exception {
        final Integer loopCount = 10;

        TargetBean targetBean = new TargetBean();
//        multiThread(loopCount, sourceBean, targetBean);

        singleThreadTest(loopCount);
    }

//    private static void multiThread(Integer loopCount, SourceBean sourceBean, TargetBean targetBean) {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        for (int i = 0; i < loopCount; i++) {
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        apacheBeanCopy.copyBean(sourceBean, targetBean);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//    }

    private static void singleThreadTest(long loopCount)
        throws Exception {
        System.out.println("---------------- apache ----------------------");
        copy(loopCount, apacheBeanCopy);

        System.out.println("---------------- cglib ----------------------");
        copy(loopCount, cglibBeanCopy);

        System.out.println("----------------- spring ---------------------");
        copy(loopCount, springBeanCopy);
        
        System.out.println("----------------- my ---------------------");
        copy(loopCount, myBeanCopy);
        
        System.out.println("----------------- fastjson ---------------------");
        copy(loopCount, fastJsonBeanCopy);

    }
    
    private  static void copy(long loopCount, BeanCopyFacade beanCopyFacade) throws Exception {
    	List<TargetBean> targetBeanList = new ArrayList<>();
    	StopWatch sw = new StopWatch();
        for (int i = 0; i < loopCount; i++) {
        	SourceBean sourceBean = new SourceBean();
        	TargetBean targeBean = new TargetBean();
            sourceBean.setId(i);
            sourceBean.setName("yzq"+i);
            sourceBean.setResult(Boolean.TRUE);
            sourceBean.setContent("bean copy test."+i);
            beanCopyFacade.copyBean(sourceBean, targeBean);
            targetBeanList.add(targeBean);
        }
        System.out.println(beanCopyFacade.toString() + "耗时：" + sw.elapsedTime());
    }
}
