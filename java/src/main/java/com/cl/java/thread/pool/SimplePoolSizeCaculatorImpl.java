package com.cl.java.thread.pool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
 
public class SimplePoolSizeCaculatorImpl extends PoolSizeCalculator {
 
    @Override
    protected Runnable creatTask() {
        return new AsyncIOTask();
    }
 
    @Override
    protected BlockingQueue<Runnable> createWorkQueue() {
        return new LinkedBlockingQueue<Runnable>(1000);
    }
 
    @Override
    protected long getCurrentThreadCPUTime() {
        return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
    }
     
    public static void main(String[] args) {
        PoolSizeCalculator poolSizeCalculator = new SimplePoolSizeCaculatorImpl();
        poolSizeCalculator.calculateBoundaries(new BigDecimal(1.0), new BigDecimal(100000));
    }
 
}
 
/**
 * 自定义的异步IO任务
 * @author Will
 *
 */
class AsyncIOTask implements Runnable {
 
    @Override
    public void run() {
        URLConnection connection = null;
        BufferedReader reader = null;
        try {
            String getURL = "http://baidu.com";
            URL getUrl = new URL(getURL);
 
            connection = getUrl.openConnection();
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
 
            String line;
            while ((line = reader.readLine()) != null) {
                // empty loop
            }
        }
 
        catch (IOException e) {
 
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                }
                catch(Exception e) {
                     
                }
            }
        }
 
    }
 
}
