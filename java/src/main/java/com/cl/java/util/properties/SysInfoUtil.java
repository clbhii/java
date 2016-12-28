/*
 * 系统名称: eden.aufw
 * 模块名称: 
 * 类 名 称: SysInfo.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.util.properties;

import java.io.InputStream;
import java.util.Properties;

import com.cl.java.util.syslog.SysLogUtil;

/**
 * 
 * @author cl 2016年12月16日
 *
 */
public class SysInfoUtil {
	private static Properties sysInfoProperties;
	
	static{	
		InputStream is = null;
		try{
			is = SysInfoUtil.class.getClassLoader().getResourceAsStream("systemInfo.properties");
			sysInfoProperties = new Properties();	
			sysInfoProperties.load(is);
		}catch(Exception e){
			SysLogUtil.getSysLog().error("加载systemInfo.propertie失败", e);
		} finally {
			if(is != null) { try{is.close();}catch(Exception e){}}
		}     
	}

	public static Properties getSysInfo(){
		return sysInfoProperties;
	}
	
}
