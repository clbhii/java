/*
 * 系统名称: eden 1.0
 * 模块名称: eden
 * 类 名 称: SocketTest.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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
public class SocketTest {
	
	public static ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue<String>(); 
	
	public static AtomicLong total=new AtomicLong();
	
	public static AtomicInteger count=new AtomicInteger();
	
	public static int threadNum=1;
	
	public static long begin =0;
	public static void main(String[] args){
		
		
		ExecutorService service=Executors.newFixedThreadPool(threadNum);
		begin = System.currentTimeMillis();
		for(int i=0;i<threadNum;i++){
			service.execute(new Runnable(){
				public void run() {
					try{
						SocketTest.run();
					}catch(Exception e){
						System.out.println(Thread.currentThread()+"失败:");
						e.printStackTrace();
					}
					count.incrementAndGet();
				}				
			});
		}
		
		
		while(true){
			//System.out.println(count.get());
			if(count.get()==threadNum){
				System.out.println("cost:"+total.get()+":"+total.get()/threadNum+".文件的数量："+queue.size());
				System.out.println("cost:"+( System.currentTimeMillis()-begin));
				System.exit(0);
			}else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void run() throws Exception{
//		long uploadTime=System.currentTimeMillis();
//		String fileId=FileClient.getInstance().uploadFile("172.16.9.88", 1234, new File("D:/upload/FasdDFS学习笔记.docx"), "21232F297A57A5A743894A0E4A801FC3");
//		System.out.println(fileId);
//		if(fileId!=null&&fileId.length()>0){
//			queue.add(fileId);
//		}	
//		total.addAndGet(System.currentTimeMillis()-uploadTime);
		
		Socket socket=null;
		try{
			socket=new Socket("127.0.0.1",1234);
			socket.setSoTimeout(10000);
			InputStream in=socket.getInputStream();
			OutputStream out=socket.getOutputStream();
			String request="hi";
			out.write(ByteUtil.intToByteArray(request.length()));
			out.write(request.getBytes("utf-8"));
			byte[] buff=new byte[4];
			try{
				in.read(buff);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			int requestLength=ByteUtil.byteArrayToInt(buff);
			buff=new byte[requestLength];
			in.read(buff);
			String response=new String(buff);
			System.out.println(Thread.currentThread()+":"+response+":"+SocketTest.count);
			out.write(response.getBytes("utf-8"));
			//socket.shutdownInput()
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//FileClient.getInstance().downloadFile("172.16.9.88", 1234, "group1/M00/00/16/rBAJWlR-dFSAGjj0AAFI0MixvTY11.docx", "21232F297A57A5A743894A0E4A801FC3", new File("D:/upload/"+new Random().nextLong()+".docx"));
		System.out.println(Thread.currentThread()+"耗时:"+(System.currentTimeMillis()-begin));
		total.addAndGet(System.currentTimeMillis()-begin);
		//FileClient.getInstance().deleteFile("127.0.0.1", 1234, "group1/M00/00/00/rBAJP1NnlgeAT39HAAFI0MixvTY68.docx", "21232F297A57A5A743894A0E4A801FC3");
	}
}
