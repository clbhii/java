/*
 * 系统名称: eden 1.0
 * 模块名称: eden
 * 类 名 称: SocketServerTest.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-12-4 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class SocketServerTest {

	public static void main(String[] args){
		try {
			ServerSocket server=new ServerSocket(1234);
			//server.accept()堵塞1000ms,如果超过1000ms没有被连接，就抛Accept timed out
			//server.setSoTimeout(100);
			Socket socket=null;
			ExecutorService service=Executors.newFixedThreadPool(500);
			while(true){			
				socket=server.accept();
				//socket.read(buff)1000ms（是否在1000ms能读完进整个数组），不能就超时。Read timed out
				//socket.setSoTimeout(1000);
				//service.submit(new Ru)
				service.submit(new MyThread(socket));
				//new MyThread(socket).run();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static class MyThread implements Runnable{
		private Socket socket=null;
		
		public MyThread(Socket socket) {
			super();
			this.socket = socket;
		}

		public void run() {
			long begin=System.currentTimeMillis();
			InputStream in=null;
			OutputStream out=null;
			try {				
				in=socket.getInputStream();
				out=socket.getOutputStream();
				byte[] buff=new byte[4];
				in.read(buff);
				int requestLength=ByteUtil.byteArrayToInt(buff);
				buff=new byte[requestLength];
				in.read(buff);
				String request=new String(buff);
				//System.out.print("获得请求："+request);
				String response=request+" ok";
				try{
					out.write(ByteUtil.intToByteArray(response.length()));
					out.write(response.getBytes("utf-8"));
				}catch(Exception e){
					e.printStackTrace();
				}		
				Thread.sleep(10);					
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(in!=null){
						in.close();
					}
					if(out!=null){
						out.close();
					}
				}catch(Exception e){
					
				}		
				if(socket!=null){
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println(" 处理请求,耗时："+(System.currentTimeMillis()-begin));
		}
		
	}
}
