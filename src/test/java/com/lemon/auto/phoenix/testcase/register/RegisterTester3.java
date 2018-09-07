package com.lemon.auto.phoenix.testcase.register;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lemon.auto.phoenix.base.BaseTester;
import com.lemon.auto.phoenix.util.ExcelUtil;

/**
 * http://39.108.136.60:8085/lmcanon_web_auto/mng/register.html
 * @author zhangying
 *
 */
public class RegisterTester3 extends BaseTester{

	@BeforeClass
	public void beforeClass() {
		driver.get("http://39.108.136.60:8085/lmcanon_web_auto/mng/register.html");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.navigate().refresh();
	}
	@Test(dataProvider="getDatas")
	public void test_all(String mobilephone, String password,String pwdconfirm, String expectResult ) {
		driver.findElement(By.id("mobilephone")).sendKeys(mobilephone);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("pwdconfirm")).sendKeys(pwdconfirm);
		driver.findElement(By.id("signup-button")).click();
		String actualResult = driver.findElement(By.className("tips")).getText();
		Assert.assertEquals(actualResult, expectResult);
		Object[][] datas = new Object[5][];
		datas[0] = new Object[5];
		datas[0][1] =5;
	}
	@DataProvider()
	public Object[][] getDatas() {
		return ExcelUtil.readExcel("/testcase/registerData.xlsx", 1, 2, 8, 1, 4);
	}
}
