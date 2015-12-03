package com.cl.db.util;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * 利用mysql客户端命令行工具
 * @author cl
 * @since 2015-04-27
 *
 */
public class MysqlUtil extends AbstractDBUtil{

	/**
	 * 执行命令
	 * @param commands
	 * @throws IOException
	 */
	public static void exec(String... commands)  throws IOException{
		exec(loadProperties(),commands);
	}	

	/**
	 * 执行命令
	 * @param properties
	 * @param commands
	 * @throws IOException
	 */
	public static void exec(Properties properties,String... commands)  throws IOException{
		Runtime runtime = Runtime.getRuntime();
		String loginCommand = LoginCommand(properties);
		//登录
		Process process = runtime.exec(loginCommand);
		OutputStream os = process.getOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(os);
		//执行命令
		for(String command: commands){
			writer.write(command);
		}	
		writer.flush();
		writer.close();
		os.close();
		String result = printOutput(process.getErrorStream());
		if(!StringUtils.isEmpty(result)){
			throw new  IOException(result);
		}
	}
	
	/**
	 * 登录命令
	 * @param properties
	 * @return
	 */
	private static String LoginCommand(Properties properties) {
		String username = properties.getProperty("username");//用户名
		String password = properties.getProperty("password");//密码
		String host = properties.getProperty("host");//导入的目标数据库所在的主机
		String port = properties.getProperty("port");//使用的端口号
		String loginCommand = new StringBuilder().append("mysql -u").append(username).append(" -p").append(password).append(" -h").append(host)
		.append(" -P").append(port).toString();
		return loginCommand;
	}
	
	/**
	 * 创建数据库命令
	 * @param dataBaseName
	 * @return
	 */
	public static String createDataBaseCommand(String dataBaseName) {
		StringBuilder command = new StringBuilder();		
		command.append("create database ").append(dataBaseName).append("; \r\n ");	
		return command.toString();
	}

	/**
	 * 删除数据库命令
	 * @param dataBaseName
	 * @return
	 */
	public static String dropDataBaseCommand(String dataBaseName) {
		StringBuilder command = new StringBuilder();		
		command.append("drop database ").append(dataBaseName).append("; \r\n ");	
		return command.toString();
	}
	
	/**
	 * 使用数据库命令
	 * @param dataBaseName
	 * @return
	 */
	public static String useDataBaseCommand(String dataBaseName) {
		StringBuilder command = new StringBuilder();		
		command.append("use ").append(dataBaseName).append("; \r\n ");
		return command.toString();
	}
	
	/**
	 * 导入sql文件命令
	 * @param filePath
	 * @return
	 */
	public static String sourceCommand(String filePath) {
		StringBuilder command = new StringBuilder();		
		command.append("source ").append(filePath).append("; \r\n");
		return command.toString();
	}
	
	/**
	 * 创建用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String createUserCommand(String userName,String password) {
		StringBuilder command = new StringBuilder();		
		command.append("create user '").append(userName).append("'@'%' identified by '").append(password).append("'; \r\n");
		return command.toString();
	}
	
	/**
	 * 用户授权 (不包括grant option)
	 * @param userName
	 * @param dataBaseName
	 * @return
	 */
	public static String grantCommand(String userName,String dataBaseName) {
		StringBuilder command = new StringBuilder();		
		command.append("grant all on ").append(dataBaseName).append(".* to '").append(userName).append("'@'%'; \r\n");
		return command.toString();
	}
	
	/**
	 * 删除用户
	 * @param userName
	 * @return
	 */
	public static String dropUserCommand(String userName) {
		StringBuilder command = new StringBuilder();		
		command.append("drop user '").append(userName).append("'@'%'; \r\n");
		return command.toString();
	}
	
	/**
	 * 备份操作
	 * @param dataBaseName
	 * @param tableNames
	 * @param filePath
	 * @throws IOException
	 */
	public static void backup(String dataBaseName, String tableNames, String filePath) throws IOException {
		backup(loadProperties(), dataBaseName, tableNames, filePath);
	}
	
	/**
	 * 备份操作
	 * @param properties
	 * @param dataBaseName
	 * @param tableNames
	 * @param filePath
	 * @throws IOException
	 */
	public static void backup(Properties properties, String dataBaseName, String tableNames, String filePath) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		String backupCommand = backupCommand(properties, dataBaseName, tableNames, filePath);
		Process process = runtime.exec(backupCommand);
		String result = printOutput(process.getErrorStream());
		if(!StringUtils.isEmpty(result)){
			throw new  IOException(result);
		}
	}
	
	/**
	 * 备份命令
	 * @param properties
	 * @param dataBaseName
	 * @param tableNames
	 * @param filePath
	 * @return
	 */
	public static String backupCommand(Properties properties, String dataBaseName, String tableNames, String filePath) {
		String username = properties.getProperty("username");//用户名
		String password = properties.getProperty("password");//密码
		String host = properties.getProperty("host");//导入的目标数据库所在的主机
		String port = properties.getProperty("port");//使用的端口号
		String backupCommand = new StringBuilder().append("mysqldump -u").append(username).append(" -p").append(password).append(" -h").append(host)
		.append(" -P").append(port).append(" ").append(dataBaseName).append(" ").append(tableNames).append(" --result-file=").append(filePath).append(" --default-character-set=utf8").toString();
		return backupCommand;
	}
}