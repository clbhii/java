/*
 * 系统名称: eden 1.0
 * 模块名称: eden.attachment
 * 类 名 称: ZipUtil.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.util.zip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;



/**
 * 
 * @author cl 2016年12月12日
 *
 */
public class ZipUtil {
	
	public static String zipSuffix=".zip";
	
	public static String zipPath="/D:/test/zip/";
	
	
	public static String zipEncode="GBK";
	
	/**
	 * 压缩文件
	 * @param zipName 压缩后的名称
	 * @param files 需要压缩的文件
	 * @return String 返回压缩文件名（如果zipName有值，就返回，没有就自定义）
	 * @throws Exception 
	 */
	public static String zip(String zipName,String[] files){
		
		if(files==null||files.length<1){
			throw new RuntimeException("需要压缩的文件不能为空");
		}
		//如果压缩名称为空，就随机生成
		if(zipName==null||zipName.trim().isEmpty()){
			zipName=UUID.randomUUID().toString()+zipSuffix;
		}
		
		File[] fileList=new File[files.length];	
		for(int i=0,l=files.length;i<l;i++){
			fileList[i]=new File(files[i]);
		}
		
		//对temp中的文件进行压缩
		doZip(zipPath+zipName,fileList);
		return zipPath+zipName;
	}
	
	
    /**
     * 压缩文件
     * @param zipName
     * @param files
     * @throws AttachmentException
     */
    public static void doZip(String zipName,File[] files) {
        try{ 
        	ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipName))); 
        	//解决中文乱码
        	zipOut.setEncoding(zipEncode);
            handleFile(files , zipOut); 
            zipOut.close(); 
        }catch(IOException e){ 
            throw new RuntimeException("压缩文件失败",e);
        } 
    }
    
	/**
	 * 必须是dirPath目录下的一些文件和文件夹的压缩
	 * @param dirPath
	 * 目录
	 * @param files
	 * 必须是同一目录下的文件和文件夹
	 * @param zipOut
	 * @throws IOException
	 */
	public static void handleDir(String dirPath, File[] files, ZipOutputStream zipOut)
			throws IOException {
		for (File file : files) {
			if(!file.exists()){
				continue;
			}
			if (file.isDirectory()) {
				File[] lf = file.listFiles();
				if (lf.length == 0) {// 如果目录为空,则单独创建之.
					// ZipEntry的isDirectory()方法中,目录以"/"结尾.
					zipOut.putNextEntry(new ZipEntry(file.toString().substring(
							dirPath.length()+1)
							+ "/"));
					zipOut.closeEntry();
				} else {
					handleDir(dirPath,lf, zipOut);
				}
			} else {
				FileInputStream fileIn = new FileInputStream(file);
				zipOut.putNextEntry(new ZipEntry(getFileName(file.toString().substring(
						dirPath.length()+1))));
				byte[] b=new byte[1024*4];
				int c=0;
				while ((c = fileIn.read(b))!=-1) {
					zipOut.write(b, 0, c);
				}
				zipOut.closeEntry();
			}
		}
	}
	
	
	/**
	 * 压缩文件和文件夹
	 * @param files
	 * 任意文件和文件夹
	 * @param zipOut
	 * @throws IOException
	 */
	public static void handleFile(File[] files, ZipOutputStream zipOut)
			throws IOException {
		for (File file : files) {	
			
			if(!file.exists()){
				continue;
			}
			String dirPaht=file.toString().substring(0, file.toString().lastIndexOf(File.separator));
			handleDir(dirPaht,new File[]{file},zipOut);
		}
	}
	
	/**
	 * 得到文件名
	 * @param fileName
	 * 3000@我们.doc
	 * @return
	 * 我们.doc
	 */
	public static String getFileName(String fileName){	
		String[] str=fileName.split("/");
		return str[str.length-1];
	}
	
	public static void main(String[] args) {
		String str = "dd/dd1";
		String[] dd = str.split("/");
		System.out.println(dd[0]);
		ZipUtil.zip("我的.zip", new String[]{"D:/test/zip/我的"})	;
	}
}
