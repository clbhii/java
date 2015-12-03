package com.cl.db.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlUtil {

	
	
	private SqlUtil(){
		
	}
	/**
	 * select * from ea_audit_log  where  create_dttm > '2015-09-22' and create_dttm < '2015-09-23' and operation_contents like '新增%' order by create_dttm limit 0,10
	 * @param sql
	 * @throws ParseException 
	 */
	public static void parse(String sql) throws ParseException {
		//解析sql
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		deal(StringToDate("2015-09-22"), StringToDate("2015-09-23"), "ea_audit_log", "select * from ea_audit_log  order by create_dttm ", 4971200, 100, result);
		System.out.println(result.size());
	}
	
	public static void deal(Date beginDate, Date endDate, String tableName, String sql,int off, int n, List<Map<String,Object>> result) {
		if(n == 0) {
			return;
		}
		String beginDateStr = dateToString(beginDate);
		String endDateStr = dateToString(endDate);
		if(beginDateStr.compareTo(endDateStr) > 0) {
			return;
		}
		//执行sql
		String executeSql = sql.replace(tableName, tableName + "_" + beginDateStr);
		List<Map<String,Object>> list = select(executeSql + " limit " + off + "," + n);
		result.addAll(list);
		n = n - list.size();
		off = 0;
		beginDate.setDate(beginDate.getDate() + 1);
		deal(beginDate, endDate, tableName, sql, off, n, result );
		
	}
	
	
	public static List<Map<String,Object>> select(String sql){
		Connection connection = null;
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		try {
			connection = ConnectionUtil.getConnection();
			ResultSet set = connection.createStatement().executeQuery(sql);
			while(set.next()) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("audit_id", set.getString(1));
				result.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeConnection(connection);
		}
		return result;
	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	
	public static Date StringToDate(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(str);
	}
	
	
	public static void main(String[] args) throws Exception {
		long begin = System.currentTimeMillis();
		parse("");
		System.out.println(System.currentTimeMillis() - begin);
	}
	
	
	
	
}
