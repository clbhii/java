/*
 * 系统名称: eden 1.0
 * 模块名称: eden
 * 类 名 称: PerformanceTest.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;



/**
 * @author xuanyin
 * 
 */
public class PerformanceTest extends AbstractJavaSamplerClient {

        /**
         * 
         */
        private static long start = 0;
        private static long end = 0;

        /**
         * 执行runTest()方法前会调用此方法,可放一些初始化代码
         */
        public void setupTest(JavaSamplerContext arg0) {
    		
                // 开始时间
                start = System.currentTimeMillis();
        }

        /**
         * 执行runTest()方法后会调用此方法.
         */
        public void teardownTest(JavaSamplerContext arg0) {

                // 结束时间
                end = System.currentTimeMillis();
                // 总体耗时
                System.err.println("cost time:" + (end - start));
        }

        /**
         * JMeter界面中可手工输入参数,代码里面通过此方法获取
         */
        public Arguments getDefaultParameters() {

                Arguments args = new Arguments();
                return args;
        }

        /**
         * JMeter测试用例入口
         */
        public SampleResult runTest(JavaSamplerContext arg0) {

                SampleResult sr = new SampleResult();

                try {
                        
                        sr.sampleStart();
                        /**
                         * Start~End内的代码会被JMeter
                         * 纳入计算吞吐量的范围内,为了使
                         * 性能结果合理,无关代码不必放此
                         */
                        /**
                         * True/False可按测试逻辑传值
                         * JMeter会对失败次数做出统计
                         */
                        sr.setSuccessful(true);
                        // End
                        sr.sampleEnd();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return sr;
        }
        
}

