package com.cl.db.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionUtil {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	static{
		try {
			dataSource.setDataSourceName("init_eden");
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/init_eden?useUnicode=true&amp;characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull");
			dataSource.setUser("eden");
			dataSource.setPassword("admin");
			dataSource.setMinPoolSize(3);
			dataSource.setMaxPoolSize(15);
			dataSource.setMaxIdleTime(1800);
		} catch (Exception e) {
			SysLog.error(e.getMessage(), e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	public static void closeConnection(Connection connection) {
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				SysLog.error(e.getMessage(), e);
			}
		}		
	}
}
