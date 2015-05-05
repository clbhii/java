package com.cl.java.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/a10007?useUnicode=true&amp;characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull";
		String jdbcUser = "eden";
		String jdbcPwd = "admin";
		return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd);
	}
	
	public static void commit(Connection con) throws SQLException {
		con.commit();
	}
	
	public static void rollback(Connection con){
		try {
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(Statement sta,Connection con){
		try{
			if(sta != null) {
				sta.close();
			}
			if(con != null) {
				con.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
