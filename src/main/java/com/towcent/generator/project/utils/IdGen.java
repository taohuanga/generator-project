package com.towcent.generator.project.utils;

import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 封装各种生成唯一性ID算法的工具类.
 */
public class IdGen {

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	public static String randomByDate() {
		StringBuffer sb = new StringBuffer(DateFormatUtils.format(new Date(), "yyMMddHHmmssSSSS"));
		//return sb.append(Math.abs(random.nextInt(1000))).toString();
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(IdGen.uuid());
		System.out.println(IdGen.uuid().length());
	}

}
