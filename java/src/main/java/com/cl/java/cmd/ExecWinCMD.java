package com.cl.java.cmd;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class ExecWinCMD {

	

	
	public static Object exec(String cmd) {
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
		String encoding = System.getProperty("file.encoding");
		System.out.println("Encoding:" + encoding);
		String pwdString = exec("ipconfig").toString();

		System.out.println("==========获得值=============");
		System.out.println(pwdString);
	}


}
