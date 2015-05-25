package com.cl.java.secret;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {

	/**
	 * 加密
	 * 
	 * @param datasource
	 * @param password
	 * @return
	 */
	public static byte[] encrypt(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// 待加密内容
		String str = "测试内容";
		str = "Product.name=cpu;Product.version=3.2;License.type=Commercial;License.expiry=2010-05-12;Server.macaddress=00-1B-77-2C-9D-8F";
		// 密码，长度要是8的倍数
		String password = "12345678";
		byte[] result = DES.encrypt(str.getBytes(Charset.forName("utf-8")), password);
		System.out.println("加密后内容为：" +result.length+":" + new String(result,"utf-8")+":"+new String(result,"utf-8").length());

		// 直接将如上内容解密
		try {
			byte[] decryResult = DES.decrypt(result, password);
			String resultStr = new String(decryResult);
			System.out.println("加密后内容为：" +decryResult.length+":"+resultStr +":"+resultStr.length());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
