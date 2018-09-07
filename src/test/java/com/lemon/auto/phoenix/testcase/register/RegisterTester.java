package com.lemon.auto.phoenix.testcase.register;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lemon.auto.phoenix.base.BaseTester;
import com.lemon.auto.phoenix.base.Locator;
import com.lemon.auto.phoenix.util.ExcelUtil;
import com.lemon.auto.phoenix.util.LocatorUtil;
import com.lemon.auto.phoenix.util.PropertiesUtil;
import com.lemon.auto.phoenix.util.ScreenShotUtil;


/**
 * 工程目录的结构是层级的、清晰--》方便管理--》有意识--》提高代码、项目的可维护性
 *1、分析需求
 *2、编写测试用例 
 *
 *反向测试用例
 * @author zhangying
 * 
 */

public class RegisterTester  extends BaseTester{

	@BeforeMethod
	public void beforeMethod() {
		driver.navigate().refresh();
	}
	
	/**
	 * 注册页面失败的测试用例
	 * 痛点：
	 * 1、数据可能变动，测试用例可能发生变化--》放到数据载体--》excel中
	 * 2、ui页面经常发生变化，定位元素的方式就可能发生变化（需求经常发生变化）--》定位元素的信息独立
	 * 出去--》放到其他的数据载体中--》xml：有规则、嵌套的数据载体
	 * 	1、用xml来描述元素的定位信息
	 * 		一个项目有多个页面
	 * 		一个页面有多个需要定位的元素
	 * @param mobilephone
	 * @param password
	 * @param pwdconfirm
	 * @param expectResult
	 * @throws InterruptedException 
	 */
	@Test(dataProvider="getDatas1")
	public void test_register_failure(String mobilephone, String password,String pwdconfirm, String expectResult ) throws InterruptedException {

	    to("registerUrl");
		type("手机号输入框", mobilephone);
		type("密码输入框", password);
		type("确认密码输入框", pwdconfirm);
		click("注册按钮");
		Thread.sleep(2000);
		assertTextPresent("提示信息", expectResult);

	}
	@DataProvider()
	public Object[][] getDatas1() {
		return ExcelUtil.readExcel("/testcase/registerData.xlsx", 1);
	}
	
	@Test(dataProvider="getDatas2")
	public void test_register_sucess(String mobilephone, String password,String pwdconfirm) throws InterruptedException {
		to("registerUrl");
		type("手机号输入框", mobilephone);
		type("密码输入框", password);
		type("确认密码输入框", pwdconfirm);
		click("注册按钮");
		Thread.sleep(2000);
		assertNotNull("登录按钮",LoginTester.class);
		
	}
	
	@DataProvider
	public Object[][] getDatas2() {
		return ExcelUtil.readExcel("/testcase/registerData.xlsx", 2);
	}
	//数据驱动：以数据去驱动自动化测试
	//数据驱动，以数据去驱动测试用例的执行，以数据去驱动自动化测试
	public static void main(String[] args) {
		LoginTester loginTester= new LoginTester();
		loginTester.getElement("登录按钮");
	}
}

