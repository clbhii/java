package com.cl.java.util.zip;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.junit.Test;

public class AntZipUtil {
	@Test
	public void test() throws Exception{
		ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("D:/test/zip/我的/dd1.zip"))); 
		zipOut.setEncoding("utf-8");
		zipOut.putNextEntry(new ZipEntry("呵呵/"));
		zipOut.closeEntry();
		zipOut.putNextEntry(new ZipEntry("dd/11/"));
		zipOut.closeEntry();
		zipOut.putNextEntry(new ZipEntry("dd/11/我.txt"));
		FileInputStream fileIn = new FileInputStream("D:/test/zip/我的/新建文本文档.txt");
		byte[] b=new byte[1024*4];
		int c=0;
		while ((c = fileIn.read(b))!=-1) {
			zipOut.write(b, 0, c);
		}
		zipOut.closeEntry();
		
		zipOut.close();
	}
	

}
