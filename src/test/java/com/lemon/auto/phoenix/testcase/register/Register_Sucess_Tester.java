package com.lemon.auto.phoenix.testcase.register;


import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lemon.auto.phoenix.base.BaseTester;
import com.lemon.auto.phoenix.base.Locator;
import com.lemon.auto.phoenix.util.ExcelUtil;
import com.lemon.auto.phoenix.util.LocatorUtil;
import com.mysql.jdbc.Driver;

public class Register_Sucess_Tester extends BaseTester{

	@BeforeClass
	public void beforeClass() {
		driver.get("http://39.108.136.60:8085/lmcanon_web_auto/mng/register.html");
	}
	@AfterMethod
	public void AfterMethod() {
		driver.navigate().back();
		driver.navigate().refresh();
	}
	/**
	 * UI层面的元素容易变动，我们定位元素的信息会发生变化--》修改源代码，麻烦--》把信息独立出去--》xml
	 * --》用xml来描述我们的定位信息
	 * 
	 * @param mobilephone
	 * @param password
	 * @param pwdconfirm
	 * @throws InterruptedException
	 */
	@Test(dataProvider="getDatas")
	public void test_register_sucess(String mobilephone, String password,String pwdconfirm) throws InterruptedException {

		getElement("手机号输入框").sendKeys(mobilephone);
		getElement("密码输入框").sendKeys(password);
		getElement("确认密码输入框").sendKeys(pwdconfirm);
		getElement("注册按钮").click();
		Thread.sleep(2000);
		WebElement element = getElement("登录按钮");
		Assert.assertNotNull(element);
		
	}
	
	@DataProvider
	public Object[][] getDatas() {
		return ExcelUtil.readExcel("/testcase/registerData.xlsx", 2);
	}
	//数据驱动：以数据去驱动自动化测试
}
