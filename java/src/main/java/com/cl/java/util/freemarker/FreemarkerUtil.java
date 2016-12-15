/*
 * 系统名称: eden 1.0
 * 模块名称: eden.gse
 * 类 名 称: FreemarkerUtil.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.util.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cl.java.util.http.MyHttpUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2015-7-11 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class FreemarkerUtil {

	private static Configuration  configuration = new Configuration();
	
	private final static String REQUEST_ENCODE = "utf-8";
	
	static{
		configuration.setDefaultEncoding(REQUEST_ENCODE);
		configuration.setClassForTemplateLoading(FreemarkerUtil.class,"/template");
	}
	
	public static void createDoc(String templateName, Object dataMap,Writer out) throws  Exception{
		Template t = configuration.getTemplate(templateName);
		t.setEncoding(REQUEST_ENCODE);
		t.process(dataMap, out);
	}
	
	public static void exportDoc(String fileName,String templateName, HttpServletResponse response, Object dataMap){
		String docName= fileName;
		Writer out = null;
		InputStream is = null;
		try {
			response.setCharacterEncoding(REQUEST_ENCODE);
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition",
					"attachment; filename=\""+MyHttpUtil.encodingFileName(docName)+"\"");
			out = response.getWriter();
			createDoc(templateName,dataMap,out);
			out.flush();	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){ 
				try {out.close();} catch (IOException e) {e.printStackTrace();}
			}
			if(is != null){ 
				try {is.close();} catch (IOException e) {e.printStackTrace();}
			}
		}
	}
	
	public static void exportDefaultDoc(String fileName,String templateName, HttpServletResponse response, Map<String,Object> dataMap){
		exportDoc(fileName + ".doc", templateName+".xml", response, dataMap);
	}
	
	public static void main(String[] args) throws IOException, Exception {
		Map<String,Object> dateMap = new HashMap<String,Object>();
		dateMap.put("itemTitle", "ddd");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();	
		map.put("taskTitle", "ddd");
		map.put("unitNames", "ddd");
		map.put("completion", "ddd");
		map.put("problem", "ddd");
		map.put("next_step", "ddd");
		list.add(map);
		list.add(map);
		list.add(map);
		list.add(map);
		list.add(map);
		dateMap.put("taskList", list);
		
//		map.put("completionNoDisplayFlag", 1);
//		map.put("problemNoDisplayFlag", 1);
//		map.put("stepNoDisplayFlag", 1);
//		FreemarkerUtil.createDoc("supervisionProcessStatus.xml", map, new FileWriter(new File("supervisionProcessStatus.doc")));
		
		//dateMap.put("completionNoDisplayFlag", 1);
		//dateMap.put("problemNoDisplayFlag", 1);
		//dateMap.put("stepNoDisplayFlag", 1);
		//FreemarkerUtil.createDoc("supervisionItem.xml", dateMap, new FileWriter(new File("supervisionItem.doc")));
		
		map = new HashMap<String,Object>();
		map.put("itemTitle", "1");
		map.put("allUnitNames", "2");
		map.put("nosignUnitNames", "3");
		map.put("signedNofeedbackUnitNames", "4");
		map.put("feedbackedUnitNames", "5");
		
		Student student = new Student();
		student.setItemTitle("1");
		
		FreemarkerUtil.createDoc("supervisionItemFeedback.xml", student, new FileWriter(new File("supervisionItemFeedback.doc")));
		
	}

}

