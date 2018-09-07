package com.lemon.auto.phoenix.testcase.register;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lemon.auto.phoenix.base.BaseTester;
import com.lemon.auto.phoenix.base.Locator;
import com.lemon.auto.phoenix.util.ExcelUtil;
import com.lemon.auto.phoenix.util.LocatorUtil;

/**
 * http://39.108.136.60:8085/lmcanon_web_auto/mng/register.html
 * @author zhangying
 *
 */
public class Register_Failure_Tester extends BaseTester{

	@BeforeClass
	public void beforeClass() {
		driver.get("http://39.108.136.60:8085/lmcanon_web_auto/mng/register.html");
	}
	
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
	@Test(dataProvider="getDatas")
	public void test_all(String mobilephone, String password,String pwdconfirm, String expectResult ) throws InterruptedException {

		
		getElement("手机号输入框").sendKeys(mobilephone);
		getElement("密码输入框").sendKeys(password);
		getElement("确认密码输入框").sendKeys(pwdconfirm);
		getElement("注册按钮").click();
		Thread.sleep(2000);
		String actualResult = getElement("提示信息").getText();
		Assert.assertEquals(actualResult, expectResult);
	}
	@DataProvider()
	public Object[][] getDatas() {
		return ExcelUtil.readExcel("/testcase/registerData.xlsx", 1, 2, 8, 1, 4);
	}
	
	//数据驱动，以数据去驱动测试用例的执行，以数据去驱动自动化测试
}
