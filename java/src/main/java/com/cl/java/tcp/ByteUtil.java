/*
 * 系统名称: eden 1.0
 * 模块名称: eden.fileserver
 * 类 名 称: ByteUtil.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.tcp;
/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-4-14 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class ByteUtil {
	public  static byte[] intToByteArray(int value){
		byte[] result=new byte[4];
		result[0]=(byte)(value>>24&0xFF);
		result[1]=(byte)(value>>16&0xFF);
		result[2]=(byte)(value>>8&0xFF);
		result[3]=(byte)(value&0xFF);
		return result;
	}
	
	public  static int byteArrayToInt(byte[] value){
		int result=0;
		for(int i=0;i<4;i++){
			result+=(value[i]&0xFF)<<(8*(3-i));
					
		}
		return result;
	}
	
	public static byte[] longToByteArray(long value){
		byte[] result=new  byte[8];
		for(int i=0;i<8;i++){
			result[i]=(byte)(value>>8*(7-i)&0xFF);
		}
		return result;
	}
	
	public static long byteArrayToLong(byte[] value){
		long result=0;
		for(int i=0;i<8;i++){
			result+=(value[i]&0xFF)<<(8*(7-i));
					
		}
		return result;
	}
}
