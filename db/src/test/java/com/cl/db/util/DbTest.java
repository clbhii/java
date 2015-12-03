package com.cl.db.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbTest {

	public static void createTable(){
		try {
			MysqlUtil.exec(MysqlUtil.useDataBaseCommand("init_eden"),MysqlUtil.sourceCommand("d:/ea_audit_log.sql"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createTestData(){
		for(int i = 0; i < 50; i++) {
			insertData();
		}
	}
	
	private static void insertData(){
		String sql = "insert into ea_audit_log_20150924 SELECT * FROM `ea_audit_log`";
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			connection.createStatement().execute(sql);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionUtil.closeConnection(connection);
		}
	}
	
	public static void main(String[] args) {
		//createTable();
		createTestData();
		System.out.println("end");
	}
}
