/*
 * 系统名称: eden 1.0
 * 模块名称: eden.common.util
 * 类 名 称: AbstractDBUtil.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.db.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.Properties;

/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2015-7-29 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public abstract class AbstractDBUtil {
	
	protected static Properties loadProperties() throws IOException{
		URL path=  MysqlUtil.class.getClassLoader().getResource("jdbc.properties");
		InputStream is = path.openStream();
		Properties propertie = new Properties();
		propertie.load(is);
		is.close();
		return propertie;
	}

	protected  static String printOutput(InputStream is) throws IOException{
		StringBuilder result = new StringBuilder();
		InputStreamReader ir = new InputStreamReader(is);
		LineNumberReader input = new LineNumberReader(ir);   
	    String line = null;   
	    while ((line = input.readLine()) != null){   
	    	System.out.println(line);
	    	result.append(line); 
		}
		input.close(); 
		ir.close();
		is.close();
		return result.toString();
	}
}
