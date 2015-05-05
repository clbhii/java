/*
 * 系统名称: eden 1.0
 * 模块名称: eden.java
 * 类 名 称: NioFile.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2015-2-13 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class NioFile {

	public static void nioCopyFile(String resource,String destination){
		try {
			FileInputStream fis=new FileInputStream(resource);
			FileOutputStream fos=new FileOutputStream(destination);
			FileChannel readChannel=fis.getChannel();
			FileChannel writerChannel=fos.getChannel();
			ByteBuffer buff=ByteBuffer.allocate(1024);
			while(true){
				buff.clear();
				int len=readChannel.read(buff);
				if(len==-1){
					break;
				}
				buff.flip();
				writerChannel.write(buff);
			}
			readChannel.close();
			writerChannel.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void nioTransfer(String resource,String destination) throws Exception{
		FileInputStream fis=new FileInputStream(resource);
		FileOutputStream fos=new FileOutputStream(destination);
		FileChannel readChannel=fis.getChannel();
		FileChannel writerChannel=fos.getChannel();
		
		readChannel.transferTo(0, readChannel.size(), writerChannel);
	}
	
	public static void main(String[] args) throws Exception{
		String from = "d:/logformat.properties";
		String to = "d:/logformat1.properties";
		String to1 = "d:/logformat2.properties";
		nioCopyFile(from,to);
		nioTransfer(from,to1);
	}
}
