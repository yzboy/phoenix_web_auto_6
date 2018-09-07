package com.lemon.auto.phoenix.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import com.lemon.auto.phoenix.base.Locator;

/**
 * 解析locator
 * @author zhangying
 * 分析：
 * 所有的测试用例都需要解析xml得到locator--》此方法定义在哪里--》
 * 需要提供一个公共公开的方法--》工具类
 * 
 *所有的页面都在一个xml文件中，所以只需要解析一次，不需要在每次都进行解析--》放到静态块
 */
public class LocatorUtil {
	
	private static HashMap<String, HashMap<String, Locator>> pageMap = new HashMap<String, HashMap<String,Locator>>();
	static {
		loadUiInfo();
	}
	public static HashMap<String, Locator> getPageMapByPageName(String pageName){
		return pageMap.get(pageName);
		
	}
	
	public static Locator getLocator(String pageName, String desc) {
		return pageMap.get(pageName).get(desc);
	}
	/**
	 * 所有的ui元素信息在一个xml中间--》解析出来
	 * @return 
	 */
	public static void loadUiInfo() {
		InputStream inputStream = LocatorUtil.class.getResourceAsStream("/locaters/locators.xml");
		SAXReader reader = new SAXReader();
		//告诉我们一个页面。我能知道它页面所有的定位信息
		
		try {
			//得到文档
			Document document = reader.read(inputStream);
			//得到跟标签
			Element rootElement = document.getRootElement();
			//得到所有的子标签
			List<Element>pageElements =  rootElement.elements();
			
			//告诉我一个元素的描述，我能知道这个元素对应的信息
			HashMap<String, Locator> locatorMap = new HashMap<String, Locator>();
			//循环遍历
			//遍历每个page
			for (Element page : pageElements) {
				//得到每个page的名称
				String pageName = page.attributeValue("name");
				//得到当前页面的所有定位信息
//				System.out.println(pageName);
				List<Element> locatorElements = page.elements();
				//遍历每个locator元素，得到每个locator对应的定位信息
				for (Element locatorElement : locatorElements) {
					//得到定位方式
					String by = locatorElement.attributeValue("by");
					//得到定位值
					String value = locatorElement.attributeValue("value");
					//得到描述信息
					String desc = locatorElement.attributeValue("desc");
					//控制台输出信息
//					System.out.println("正在以【"+by+"】"+"的方法方式值为【"+value+"】"+"定位"+desc+"元素");
					//解析出来后包装到另外的数据载体--》方便java代码进行操作--》对象
					Locator locator = new Locator(by, value, desc);
					//每个locator要放到某个容器中去
					//怎么去包装这里一个一个定位信息，可以进行方便的查找
					
				
					locatorMap.put(desc, locator);
				}
				pageMap.put(pageName, locatorMap);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
	}
}
