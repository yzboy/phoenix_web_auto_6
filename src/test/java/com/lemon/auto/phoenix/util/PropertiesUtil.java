package com.lemon.auto.phoenix.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 操作properties文件的工具类
 * 
 * @author zhangying
 *
 */
public class PropertiesUtil {

	private static Properties urlProperties = new Properties();
	private static Properties jdbcProperties = new Properties();
	static {
		// 解析url
		loadUrlProperties();
		// 解析jdbc
	}

	/**
	 * 加载属性文件
	 */
	private static void loadUrlProperties() {
		// TODO Auto-generated method stub

		try {
			urlProperties.load(PropertiesUtil.class.getResourceAsStream("/url.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 根据指定的key获取对应的url
	 */
	public static String getUrl(String key) {
		return urlProperties.getProperty(key);
	}
	
	public static void main(String[] args) {
		String url =  getUrl("registerUrl");
		System.out.println(url);
	}
}
