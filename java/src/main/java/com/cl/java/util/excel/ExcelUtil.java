package com.cl.java.util.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.tools.ant.util.StringUtils;

import com.alibaba.fastjson.JSON;
/**
 * https://blog.csdn.net/traguezw/article/details/79063965
 * Author: clbhii@163.com
 * JDK: 1.8
 * Created on 2016年4月12日
 */
public class ExcelUtil {
	

	public static<T> void export(HttpServletResponse response, String fileName, String[] headerNames, String[] headerKeys, List<T> list) {
		// 清空response
		response.reset();
		// 设置response的Header
		String encodingFileName = encodingFileName(fileName);
		response.addHeader("Content-Disposition", "attachment;filename="
				+ encodingFileName + ";filename*=utf-8''" + encodingFileName);
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
//            if (returnFileName.length() > 150) { 
//                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1"); 
//                returnFileName = StringUtils.replace(returnFileName, " ", "%20"); 
//            } 
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace(); 
           
        } 
        return returnFileName; 
    } 
	
	public static List<Map<String, String>> read(String filePath, String[] columns, int startRow) throws Exception{
		List<Map<String, String>> list = new ArrayList<>();
		 //创建工作薄对象
		InputStream inputStream = new FileInputStream(filePath);
        HSSFWorkbook workbook=new HSSFWorkbook(inputStream);
        //创建工作表对象
        HSSFSheet sheet=workbook.getSheet("sheet1");
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i = startRow - 1; i < rows; i++) {
        	HSSFRow row = sheet.getRow(i);
        	Map<String, String> map = new HashMap<>();
        	for(int j = 0; j < columns.length; j++) {
        		HSSFCell cell = row.getCell(j);
        		map.put(columns[j], cell.getStringCellValue());
        	}
        	list.add(map);
        }
        return list;
	}
	
	public static void main(String[] args) throws Exception {
		List<Map<String, String>> list = read("G:/test/test.xls", new String[]{"账号", "密码"}, 2);
		System.out.println(JSON.toJSONString(list));
	}
}
