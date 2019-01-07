package com.cl.java.util.excel;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.tools.ant.util.StringUtils;

import javassist.ClassClassPath;
import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;
/**
 * Author: clbhii@163.com
 * JDK: 1.8
 * Created on 2016年4月12日
 */
public class ExcelUtil2 {
	
	private static Map<String,ObjFieldValueConvert> objFieldValueConvertMap = new HashMap<>();
	
    public static Map<String, Object> convertAllFieldValue(Object obj) {
        String name = obj.getClass().getSimpleName()+"2Map";
        ObjFieldValueConvert convert = objFieldValueConvertMap.get(name);
        if (convert == null){
            synchronized (objFieldValueConvertMap){
            	convert = objFieldValueConvertMap.get(name);
                if (convert != null) {
                    return convert.convertAllFieldValue(obj);
                }else {
                	convert = createClass(obj, name);
                	objFieldValueConvertMap.put(name, convert);
                    return convert.convertAllFieldValue(obj);
                }
            }
        }else {
            return convert.convertAllFieldValue(obj);
        }

    }
	
    public static ObjFieldValueConvert createClass(Object obj,String name){
        ClassPool classPool = ClassPool.getDefault();
        //创建类 名称为 name
        CtClass ctClass = classPool.makeClass(name);
        //添加ChaneUtil接口的路径 防止加载错误  【不能删除】
        ClassPath classPath = classPool.insertClassPath(new ClassClassPath(ObjFieldValueConvert.class));
        String objClassName = obj.getClass().getName();
        Field[] objClassFields = obj.getClass().getDeclaredFields();
        try {
            //获取接口对象 采用全路径 防止出现找不到对象
            CtClass interfaceCtClass = classPool.get(ObjFieldValueConvert.class.getName());
            //添加新家对象接口
            ctClass.addInterface(interfaceCtClass);
            //拼接方法体
            StringBuilder src = new StringBuilder();
            //方法名称 以及对参数的强转型
            src.append("public " + Map.class.getName() +"  convertAllFieldValue(Object o){")
                    .append(objClassName + " var1 = (" + objClassName + ")$1;")
                    .append(Map.class.getName() + " maps = new " + HashMap.class.getName()+ "();");
            for (int i = 0; i < objClassFields.length; i++ ) {
            	String fieldName = objClassFields[i].getName();
                String getMethodName = "get" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
                src.append("maps.put(\""+ fieldName + "\", var1." + getMethodName + "());\n");

            }

            src.append(" return maps;}");
            //向新建对象中添加方法
            ctClass.addMethod(CtNewMethod.make(src.toString(), ctClass));
            return (ObjFieldValueConvert) ctClass.toClass().newInstance();
        }catch (Exception e) {
            throw new RuntimeException("生成对象失败");
        }
    }
    
    
	public static<T> void export(HttpServletResponse response, String fileName, String[] headerNames, String[] headerKeys, List<T> list) {
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition", "attachment;filename="
				+ encodingFileName(fileName));
		response.setContentType("application/octet-stream");
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			Workbook workbook = null;
			if(fileName.endsWith(".xls")){
				 // 声明一个工作薄
				workbook = new HSSFWorkbook();
			}else {
				workbook = new SXSSFWorkbook(10000);
			}
			
	        // 生成一个表格
			Sheet sheet = workbook.createSheet("Sheet1");
	        // 设置表格默认列宽度为18个字节
	        sheet.setDefaultColumnWidth(18);
	       
	        Row row = sheet.createRow(0);
	        for(int colIndex = 0; colIndex < headerNames.length; colIndex++){
	        	Cell cell = row.createCell(colIndex);
	        	cell.setCellValue(headerNames[colIndex]);
	        }
	        for (int rowIndex = 0; rowIndex < list.size() ; rowIndex++) {
				row = sheet.createRow(rowIndex + 1);
				T t = list.get(rowIndex);
				Map<String, Object> fieldValueMap = convertAllFieldValue(t);
				
				for(int colIndex = 0; colIndex < headerKeys.length; colIndex++){
		        	Cell cell = row.createCell(colIndex);
					Object fieldValue = fieldValueMap.get(headerKeys[colIndex]);
					
					if(fieldValue == null) {
						continue;
					}
					Object type = fieldValue.getClass();
					if (type.equals(BigDecimal.class)) {		
						cell.setCellValue(((BigDecimal)fieldValue).doubleValue());
					} else if (type.equals(Integer.class)) {
						cell.setCellValue((Integer)fieldValue);
					}else if (type.equals(Long.class)) {
						cell.setCellValue(((Long)fieldValue).toString());
					} else if (type.equals(Double.class)) {
						cell.setCellValue(((Double)fieldValue).doubleValue());
					} else if (type.equals(Date.class)) {
						cell.setCellValue(DateFormatUtils.format((Date)fieldValue, "yyyy-MM-dd"));
					}else {
						cell.setCellValue((String)fieldValue);
					}
		        	
		        }			
			}        
	        workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(out);
        }
	}
	
	public static String encodingFileName(String fileName) { 
        String returnFileName = ""; 
        try { 
            returnFileName = URLEncoder.encode(fileName, "UTF-8"); 
            returnFileName = StringUtils.replace(returnFileName, "+", "%20"); 
            if (returnFileName.length() > 150) { 
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1"); 
                returnFileName = StringUtils.replace(returnFileName, " ", "%20"); 
            } 
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace(); 
           
        } 
        return returnFileName; 
    } 
}
