package com.lemon.auto.phoenix.base;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.lemon.auto.phoenix.util.LocatorUtil;
import com.lemon.auto.phoenix.util.PropertiesUtil;
import com.lemon.auto.phoenix.util.SeleniumUtil;

/**
 * 基础类 其实就是抽取父类，封装webDriver所具有的方法 对selenium所具有的API实现二次封装
 * 
 * @author zhangying 以后在编写代码的过程中，为了减少耦合性，可以将具有共性的方法都抽取到父类中
 *
 */
public class BaseTester {

	public static WebDriver driver;
	public static Logger logger = Logger.getLogger(BaseTester.class);

	@BeforeSuite
	// 通过parameter可以注入参数--》自动注入形式参数的
	@Parameters(value = { "browserType", "driverPath", "seleniumVersion" })
	public void beforeSuite(String browserType, String driverPath, String seleniumVersion) {
		driver = SeleniumUtil.getWebDriver(browserType, driverPath, seleniumVersion);
	}

	@AfterSuite
	public void afterSuite() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();

	}

	/**
	 * 
	 * @param timeOutInseconds
	 * @param by
	 * @return
	 */
	public WebElement getElement(long timeOutInseconds, final By by) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInseconds);
		// until：在超时时间内满足某个条件（才能进行下一步）或者超时也没满足（异常）
		WebElement element = wait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(by);
			}

		});
		return element;

	}

	public WebElement getElement(final By by) {
		return getElement(5, by);

	}

	/**
	 * 获得指定页面的元素
	 * 
	 * @param timeOutInseconds
	 * @param desc
	 * @return
	 */

	public WebElement getElement(long timeOutInseconds, String desc, Class<?> clazz) {

		HashMap<String, Locator> locatorMap = LocatorUtil.getPageMapByPageName(clazz.getName());
		System.out.println(this.getClass().getName());
		System.out.println(locatorMap);
		Locator locator = locatorMap.get(desc);
		final String by = locator.getBy();
		final String value = locator.getValue();
		logger.info("正在以【" + by + "】" + "方式," + "值为：【" + value + "】" + "定位：" + locator.getDesc() + "元素");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInseconds);
		// until：在超时时间内满足某个条件（才能进行下一步）或者超时也没满足（异常）
		WebElement element = wait.until(new ExpectedCondition<WebElement>() {
			By by2 = null;

			public WebElement apply(WebDriver driver) {
				//
				// 拿到字节码对象：现在要拿到By类中的指定方法
				Class<By> clazz = By.class;
				// 获得方法
				try {
					Method method = clazz.getMethod(by, String.class);
					// 反射调用
					// 静态方法的调用，所以对象传null
					by2 = (By) method.invoke(null, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return driver.findElement(by2);
			}

		});
		return element;
	}

	public WebElement getElement(long timeOutInseconds, String desc) {
		return getElement(timeOutInseconds, desc, this.getClass());
	}

	/**
	 * 封装成我们自己的框架，对于后期如果引用的框架发生变化，便于更换 type:打字：往一个元素输入内容
	 * 
	 * @param content
	 *            向元素中要输入的内容
	 * @param keyword
	 *            元素的关键字描述
	 */
	public void type(String keyword, String content) {
		logger.info("往【" + keyword + "】输入内容：【" + content + "】");
		getElement(keyword).sendKeys(content);
	}

	/**
	 * 点击某个元素
	 * 
	 * @param keyword
	 */
	public void click(String keyword) {
		logger.info("正在点击" + keyword + "元素");
		getElement(keyword).click();

	}

	/**
	 * 得到页面元素的文本
	 * 
	 * @param keyword
	 *            元素的描述关键字
	 * @return 该元素的文本内容
	 */
	public String getText(String keyword) {
		return getElement(keyword).getText();
	}

	/**
	 * 打开某个网址
	 */
	public void to(String urlKey) {
		driver.get(PropertiesUtil.getUrl(urlKey));
	}

	public void forward() {
		driver.navigate().forward();
	}

	public void back() {
		driver.navigate().back();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public void maximize() {
		driver.manage().window().maximize();
	}

	/**
	 * 断言某页面元素的文本值为某文本
	 */
	public void assertTextPresent(String key, String expectedText) {
		String actualText = getText(key);
		Assert.assertEquals(actualText, expectedText);
	}

	public void assertNotNull(String key,Class<?>clazz) {
		Assert.assertNotNull(getElement(key, clazz));
	}

	public WebElement getElement(String desc) {
		return getElement(5, desc);

	}

	public WebElement getElement(String desc, Class<?> clazz) {
		return getElement(5, desc, clazz);

	}
}
