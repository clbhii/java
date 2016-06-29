package com.cl.apache.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

public class ShiroTest {
	//通过如上方式可以进行base64编码/解码操作
	@Test
	public void test1() throws Exception{
		
		String str = "hello";
		String base64Encoded = Base64.encodeToString(str.getBytes("utf-8"));
		print(base64Encoded);
		String str2 = Base64.decodeToString(base64Encoded);
		print(str2);
		Assert.assertEquals(str, str2);
	}
	//过如上方式可以进行16进制字符串编码/解码操作
	@Test
	public void test2() throws Exception{
		
		String str = "hello";
		String base64Encoded = Hex.encodeToString(str.getBytes());
		print(base64Encoded);
		String str2 = new String(Hex.decode(base64Encoded.getBytes()));
		print(str2);
		Assert.assertEquals(str, str2);
	}
	/**
	 * 散列算法一般用于生成数据的摘要信息，是一种不可逆的算法，一般适合存储密码之类的数据，常见的散列算法如MD5、SHA等。
	 * 一般进行散列时最好提供一个salt（盐），比如加密密码“admin”，产生的散列值是“21232f297a57a5a743894a0e4a801fc3”，
	 * 可以到一些md5解密网站很容易的通过散列值得到密码“admin”，即如果直接对密码进行散列相对来说破解更容易，
	 * 此时我们可以加一些只有系统知道的干扰数据，如用户名和ID（即盐）；这样散列的对象是“密码+用户名+ID”，
	 * 这样生成的散列值相对来说更难破解。
	 * 
	 * 如上代码通过盐“123”MD5散列“hello”。另外散列时还可以指定散列次数，如2次表示：md5(md5(str))：
	 * “new Md5Hash(str, salt, 2).toString()”。
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		String str = "hello";
		String salt = "123";
		String md5 = new Md5Hash(str, salt).toString();//还可以转换为 toBase64()/toHex()&nbsp;
		print(md5);
		md5 = new Md5Hash(str, salt, 2).toString();
		print(md5);
	}
	
	/**
	 * 使用SHA256算法生成相应的散列数据，另外还有如SHA1、SHA512算法。
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		String str = "hello";
		String salt = "123";
		String sha1 = new Sha256Hash(str, salt).toString();
		print(sha1);
	}
	/**
	 * 通用的散列
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception {
		String str = "hello";
		String salt = "123";
		//内部使用MessageDigest
		String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
		print(simpleHash);
		String md5 = new SimpleHash("md5", str, salt).toString();
		print(md5);
	}
	
	/**
	 * SimpleHash的简单封装
	 * @throws Exception
	 */
	@Test
	public void test6() throws Exception {
		DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无
		hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
		hashService.setHashIterations(1); //生成Hash值的迭代次数

		HashRequest request = new HashRequest.Builder()
		            .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
		            .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
		String hex = hashService.computeHash(request).toHex();
		print(hex);
	}
	/**
	 * 随机数
	 * @throws Exception
	 */
	@Test
	public void test7() throws Exception {
		SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		randomNumberGenerator.setSeed("123".getBytes());
		String hex = randomNumberGenerator.nextBytes().toHex();
		print(hex);
	}
	
	private void print(String message){
		System.out.println(message);
	}
}
