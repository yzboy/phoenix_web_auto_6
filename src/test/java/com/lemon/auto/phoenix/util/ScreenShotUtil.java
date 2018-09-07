package com.lemon.auto.phoenix.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.lemon.auto.phoenix.base.BaseTester;

public class ScreenShotUtil {

	public static File takeScreenShot(String reportDir) {
		//TakesScreenshot是remoteDriver所实现的接口，所以可以向上转型为TakesScreenshot类型
		TakesScreenshot takesScreenshot = (TakesScreenshot) BaseTester.driver;
		//调用getScreenshotAs方法将得到的截屏数据存储为指定类型
		File screenFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		//按照当前时间的毫秒数返回当前日期，作为文件名
		long time = new Date().getTime();
		File file = new File(reportDir+File.separator+time+".png");
		try {
			//apache下的FileUtil工具包中的copyFile方法可以实现文件copy到另一个文件中
			FileUtils.copyFile(screenFile, file);
			return file;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return null;
	}
	
}
