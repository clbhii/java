package com.cl.java.util.excel;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.tools.ant.util.StringUtils;
/**
 * Author: clbhii@163.com
 * JDK: 1.8
 * Created on 2016年4月12日
 */
public class ExcelUtil {
	

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
				Reflector reflector = Reflector.forClass(t.getClass());
				
				for(int colIndex = 0; colIndex < headerKeys.length; colIndex++){
		        	Cell cell = row.createCell(colIndex);
					Invoker invoker = reflector.getGetInvoker(headerKeys[colIndex]);
					Object fieldValue = invoker.invoke(t, new Object[] {});
					
					if(fieldValue == null) {
						continue;
					}
					Object type = invoker.getType();
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
