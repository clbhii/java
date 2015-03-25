/*
 * 系统名称: eden 1.0
 * 模块名称: eden
 * 类 名 称: VilatileTest.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.thread;
/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-12-25 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class VilatileTest {

	
	public static void main(String[] args){
		Request request=new Request();
		MyThread my=new MyThread(request);
		my.start();
		try {
			Thread.sleep(3000);
			my.j=2;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static class Request{
		
		private volatile  boolean flag=false;
		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}
		
		
	}
	
	static class MyThread extends Thread{
		private Request request;
		private   boolean flag=false;
		
		public int j=1;
		public MyThread(Request request) {
			super();
			this.request = request;
		}
		

		public boolean isFlag() {
			return flag;
		}


		public void setFlag(boolean flag) {
			this.flag = flag;
		}


		@Override
		public void run() {
			int i=0;
			while(!flag){
				System.out.println(j);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
