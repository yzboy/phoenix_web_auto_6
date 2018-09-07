package com.lemon.auto.phoenix.testcase.register;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lemon.auto.phoenix.base.BaseTester;

/**
 * http://39.108.136.60:8085/lmcanon_web_auto/mng/register.html
 * @author zhangying
 *
 */
public class RegisterTester2 extends BaseTester{

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
		driver.findElement(By.id("password")).sendKeys(mobilephone);
		driver.findElement(By.id("pwdconfirm")).sendKeys(mobilephone);
		driver.findElement(By.id("signup-button")).click();
		driver.findElement(By.id("expectResult")).sendKeys(mobilephone);
		String actualResult = driver.findElement(By.className("tips")).getText();
		Assert.assertEquals(actualResult, expectResult);
	}
	@DataProvider
	public void getDatas() {
		
	}
}
