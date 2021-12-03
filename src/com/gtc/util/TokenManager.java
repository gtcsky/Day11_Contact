package com.gtc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

import com.sun.mail.util.BASE64EncoderStream;

public class TokenManager {

	private static TokenManager manager=new TokenManager();
	private TokenManager() {
		//构造方法私有化,使用单例模式
	}
	
	public static TokenManager newInstance(){
		return manager;

	}
	
	/**
	 * 产生令牌
	 * @return
	 */
	public static String getToken(){
		String str=System.currentTimeMillis()+Math.random()+"";
		System.out.println("md5编码�? str="+str);
		try {
			MessageDigest md=MessageDigest.getInstance("md5");	//获取md5码编码器			
			byte[] bytes=md.digest(str.getBytes());							//把str进行md5编码
			System.out.println("md5编码�? bytes="+new String(bytes));
			//由于md5编码后的码如果直接转换成string可能会在查码表时产生非可见字符,所以用base64再次进行编码
			Base64 encoder=new Base64();
			str=new String(encoder.encode(bytes));
			//System.out.println("Base64编码�? str="+str);
			
		} catch (NoSuchAlgorithmException e) {
		throw new RuntimeException(e);
		
		
		}
		return str;
	}
	/**
	 * 产生令牌
	 * @return
	 */
	public static String edcode(String str){
		//String str=System.currentTimeMillis()+Math.random()+"";
		if(str==null||str.isEmpty()){
			throw new RuntimeException("密码不能为空");
			}	
		System.out.println("---------------------"   );
		try {
			MessageDigest md=MessageDigest.getInstance("md5");	//获取md5码编码器			
			byte[] bytes=md.digest(str.getBytes());							//把str进行md5编码
			//System.out.println("md5编码�? bytes="+new String(bytes));
			//由于md5编码后的码如果直接转换成string可能会在查码表时产生非可见字�?,�?以用base64再次进行编码
			Base64 encoder=new Base64();
			str=new String(encoder.encode(bytes));
			//System.out.println("Base64编码�? str="+str);
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 编码错误");
			
			
		}
		return str;
	}
}
