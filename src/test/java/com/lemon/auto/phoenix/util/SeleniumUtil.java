package com.lemon.auto.phoenix.util;
/**
 * Selenium的一个工具类
 * @author zhangying
 *
 */


/**
 * Selenium的一个工具类
 * @author zhangying
 *
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

public class SeleniumUtil {
	/**
	 * 创建浏览器对应的驱动：ChromeDriver、FirefoxDriver、IEDriver
	 * 
	 * 驱动的路径--》硬编码：--》配置文件：properties--》自己实现 command+t显示该类的继承关系
	 * 
	 * 硬编码：驱动的路径--》配置文件
	 */

	//ie浏览器的名字
	public static final String IE_BROWSER_NAME = "ie";
	//chrome浏览器的名称
	public static final String CHROME_BROWSER_NAME = "chrome";
	//firefox浏览器的名称
	public static final String FIREFOX_BROWSER_NAME = "firefox";

	/**
	 * 根据配置创建webdriver类型
	 * 
	 * @param browserType
	 *            浏览器类型
	 * @param driverPath
	 *            可执行驱动文件的路径
	 * @param seleniumVersion
	 *            selenium的版本号
	 * @return
	 */
	//3个浏览器--》10个浏览器--》10个分支--》200行代码--》修改、维护不方便--》定位到代码不方便
	//--》if分支--》分而治之的思想-->不断抽取方--》可维护性、可扩展性大大提高
	public static WebDriver getWebDriver(String browserType, String driverPath, String seleniumVersion) {
		if (CHROME_BROWSER_NAME.equalsIgnoreCase(browserType)) {
			return getChromeDriver(driverPath);
		}
		if (IE_BROWSER_NAME.equalsIgnoreCase(browserType)) {
			return getIEDriver();
		}
		if (FIREFOX_BROWSER_NAME.equalsIgnoreCase(browserType)) {
			return getFireFoxDriver(seleniumVersion);
		}
		return null;
	}

	private static WebDriver getIEDriver() {
		System.setProperty(SystemProperty.BROWSER_BINARY, "");
		return new InternetExplorerDriver();
	}

	//TDD 未完成的事情
	//FIXME 需要订正的错误--》在tasks中打开显示
	private static WebDriver getFireFoxDriver(String seleniumVersion) {
		System.setProperty(SystemProperty.BROWSER_BINARY, "/Applications/Firefox.app/Contents/MacOS/firefox-bin");
		if (seleniumVersion.compareTo("3") >0) {
			System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, "驱动文件路径");
		}
		return new FirefoxDriver();
	}

	private static WebDriver getChromeDriver(String driverPath) {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);
		return new ChromeDriver();
	}
}
