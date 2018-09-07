package com.lemon.auto.phoenix.testcase.register;

import java.io.File;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.lemon.auto.phoenix.util.PropertiesUtil;
import com.lemon.auto.phoenix.util.ScreenShotUtil;
/**
 * 部署：
 * 1、部署到达局域网的服务器--》192.168.199.209:XXXX/XXX
 * 2、部署到广域网的服务器--》数据中心、阿里云、腾讯云（服务器）
 * 部署报表的步骤：
 * 1、下载解压apache
 * 2、以管理员身份打开cmd窗口
 * 3、安装 
 * 4、修改配置（4个地方：severRoot、documentRoot、Liberay、Listening）
 * 5、启动
 * 6、验证
 * 如果想修改reportng页面现实的内容，比如增加截屏放上去，可以修改reportng.jar包
 * 修改reportng.jar包
 * 1、源代码--》修改--》编译--》打包成一个新的jar包
 * 2、修改jar包（压缩包）
 * @author zhangying
 *
 */


public class PhoenixListenner extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailure(tr);
		//ITestResult可以得到testng执行时所有的信息，包括测试失败。成功，断言，以及生成的报表
		//tr.getTestContext()就可以得到的所有的测试执行后的信息
		String dir = tr.getTestContext().getOutputDirectory();
		//reportDir:Users/zhangying/eclipse-workspace/phoenix_web_auto_6/target/surefire-reports/Suite
		//通用方法：reportDir要动态生成，根据不同的test放入不同的文件中
		String reportDir = dir.replace(dir.substring(dir.lastIndexOf(File.separator)+1), "screenshot"+File.separator+tr.getTestContext().getName());
		//添加图片
		
		//首先生成图片地址:http://127.0.0.1:8080/screenshot/Register/1536028822418.png
		//1 得到图片本地的绝对路径:/Users/zhangying/eclipse-workspace/phoenix_web_auto_6/target/surefire-reports/screenshot/Register
		String absolutePath = ScreenShotUtil.takeScreenShot(reportDir).getAbsolutePath(); 
		//截取字符串
		String oldStr = absolutePath.substring(0,absolutePath.indexOf("screenshot"));
		//baseUrl地址
		String baseUrl= PropertiesUtil.getUrl("baseUrl");
		//替换字符串
		String tempUrl= absolutePath.replace(oldStr, baseUrl);
		
//		String imgUrl = tempUrl.replaceAll("\\", "/");
		String imgUrl =tempUrl;
		System.out.println("##################################");
		System.out.println(tempUrl);
		Reporter.log("<img src='"+imgUrl+"' hight='100' width='100'><a href='"+imgUrl+
				"' target='_blank'>点击查看大图</a></img>");
		
	}
	
}
