package com.cl.java.cmd;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * java在linux环境下执行linux命令，然后返回命令返回值。
 * 
 * @author lee
 */
public class ExecLinuxCMD {

	public static Object exec(String[] cmd) {
		try {
		
			Process process = Runtime.getRuntime().exec(cmd);
			LineNumberReader br = new LineNumberReader(new InputStreamReader(
					process.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sb.append(line).append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] cmd = { "/bin/sh", "-c", "pwd" };
		System.out.println(exec(cmd).toString());
		
		cmd = new String[] {"pwd"};
		System.out.println(exec(cmd).toString());

	}

}