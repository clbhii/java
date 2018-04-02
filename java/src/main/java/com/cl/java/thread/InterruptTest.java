/*
 * 系统名称: eden 1.0
 * 模块名称: eden
 * 类 名 称: InterruptTest.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

import org.junit.Test;

/**
 * 
 * 
 * 
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-12-9 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class InterruptTest {

	public static void main(String[] args){

	}
	/**
	 * 在线程启动后我们在InterruptTest.sleep(500)后,针对启动的线程进行了thread.interrupt(),
	 * 然而thread.isInterrupted()也返回了true,但是线程依然正在运行。
	 * 因为我们这里的sleep是自己写的并不是用的Thread.sleep或io通道
	 * 所以无法通过exception进行线程终端捕获，线程也不会抛出exception
	 */
	@Test
	public void test1() {
		MyThread thread = new MyThread();  
        System.out.println("躁起来修电脑去");  
        System.out.println("interrupt state = " + thread.isInterrupted());  
        thread.start();  
        InterruptTest.sleep(500);  
        thread.interrupt();  
        System.out.println("别修复了,世界末日了");  
        System.out.println("interrupt state = " + thread.isInterrupted());  
        try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * interrupt操作的线程处于sleep,wait,join 阻塞等状态的时候，会抛出一个InterruptedException
	 * 中断状态重置了所以返回false了
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception{
		MyThread1 thread = new MyThread1();  
        System.out.println("躁起来修电脑去");   
        System.out.println("interrupt state = " + thread.isInterrupted());  
        thread.start();  
        Thread.sleep(500);  
        thread.interrupt();  
        System.out.println("别修复了,世界末日了");  
        /** 
         * 因为Thread.sleep 触发了中断状态 所以interrupted状态被清除 
         * 所以这里打印的也是false 
         */  
        System.out.println("interrupt state = " + thread.isInterrupted()); 
        
        thread.join();
	}
	/**
	 * Interrupt操作的线程在可中断通道上因调用某个阻塞的 I/O 操作(serverSocketChannel. accept()、socketChannel.connect、socketChannel.open、 
		socketChannel.read、socketChannel.write、fileChannel.read、fileChannel.write)，会抛出一个ClosedByInterruptException
		不重置状态
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception{
		MyThread2 thread = new MyThread2();  
        System.out.println("躁起来修电脑去");   
        System.out.println("interrupt state = " + thread.isInterrupted());  
        thread.start();  
        Thread.sleep(800);  
        thread.interrupt();  
        System.out.println("别修复了,世界末日了");  
        System.out.println("interrupt state = " + thread.isInterrupted()); 
        
        thread.join();
	}
	
	
	static class MyThread extends Thread{

		@Override
		public void run() {
			System.out.println("开始修复电脑");  
	        for (int i = 10; i <= 100; i += 10) {  
	            if(Thread.interrupted())  
	                System.out.println("~~别烦我我得修完了");  
	            System.out.println("修复进度" + i + "%");  
	            InterruptTest.sleep(200);  
	        }  
	        System.out.println("修复完毕");  
		}
	}
	
	static class MyThread1 extends Thread{

		@Override
		public void run() {
			System.out.println("开始修复电脑");  
	        for (int i = 10; i <= 100; i += 10) {  
	            if (Thread.interrupted())  
	                System.out.println("~~别烦我我得修完了");  
	            System.out.println("修复进度" + i + "%");  
	                try {  
	                    Thread.sleep(200);  
	                } catch (InterruptedException e) {  
	                    e.printStackTrace();  
	                    System.out.println("抛异常了被try/catch死神捕获了,只能return了");  
	                    return;  
	                }  
	        }  
	        System.out.println("修复完毕");  
		}
	}
	
	
	static class MyThread2 extends Thread{

		@Override
		public void run() {
	        System.out.println("开始修复电脑");  
	        for (int i = 10; i <= 100; i += 10) {  
	            if (Thread.interrupted())  
	                System.out.println("~~别烦我我得修完了");  
	            System.out.println("修复进度" + i + "%");  
	            if (i == 70) {  
	                try {  
	                    System.out.println("建立一个nio ServerSocketChannel我就继续修复");  
	                    ServerSocketChannel ss = ServerSocketChannel.open();  
	                    ss.socket().bind(new InetSocketAddress(4488));  
	                    System.out.println("建立好了");  
	                    while (true) {  
	                        // 阻塞一下  
	                        ss.accept();  
	                    }  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                    System.out.println("抛异常了被try/catch死神补货了,只能return了");  
	                    return;  
	                }  
	            }  
	  
	        }  
	        System.out.println("修复完毕"); 
		}
	}
	
	 /** 
     * 自己写个sleep条件循环为了禁止Interrupt对Thread.sleep(x)时的异常抛出 
     * @param step 
     * @author Allen 
     * @date 2017年2月21日 
     */  
    private static void sleep(int step) {  
        long time = System.currentTimeMillis();  
        while ((System.currentTimeMillis() - time < step)) {  
        }  
    }  
	
}
