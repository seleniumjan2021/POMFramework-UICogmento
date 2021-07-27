package com.hcl.cogmento.helper.testbase;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.hcl.cogmento.helper.extent.ExtentManager;
import com.hcl.cogmento.helper.logger.LoggerHelper;
import com.hcl.cogmento.helper.resources.ResourceHelper;
import com.hcl.cogmento.helper.util.TestUtil;

public class TestBase {
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;
	
	public static String reportType = "";
	public static String executionLocation = "";
	//public static String appTestVersion = "";
	
	private static Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportFolder , extentReportFolder , logFolder, screenShotPath;
	
	@BeforeSuite
	public void beforeSuite(ITestContext testContext) {
		
		Map<String, String> params = testContext.getCurrentXmlTest().getAllParameters();
		TestBase.reportType = params.get("reportType");
		TestBase.executionLocation = params.get("location");
	
		if(executionLocation == null && reportType == null) {
			executionLocation = "local";
			reportType = "CustomerBilling";
		}
		log.info("Before Suite Report Type" +TestBase.reportType + " , Execution Location" +TestBase.executionLocation);
		extent = ExtentManager.getReportInstance();
		reportFolder = new File(ResourceHelper.getReportPath());
		extentReportFolder = new File(ResourceHelper.getReportPath(TestUtil.extentReport));
		logFolder = new File(ResourceHelper.getReportPath(TestUtil.logFolder));
		screenShotPath = new File(ResourceHelper.getReportPath(TestUtil.screenShotPath));
	}
	
	@AfterSuite
	public void afterSuite() {
		if(StringUtils.containsIgnoreCase(reportType, "uiComento")) {	
			//This for writing in the console log
			log.info("Automation Run Completed on UICogmento app version" + TestUtil.appUICogmentoVersion);	
			//This one will write in extent report
			logInfoExtentReport("Automation Run Completed on UICogmento app version" + TestUtil.appUICogmentoVersion);
		}else {
			log.info("Automation Run Completed on UIProducts app version" + TestUtil.appUIProductsVersion);
			logInfoExtentReport("Automation Run Completed on UIProducts app version" + TestUtil.appUIProductsVersion);
		} 
	}
	
	@BeforeClass
	public void beforeClass() {
		test = extent.createTest(getClass().getSimpleName());
	}
	
	@AfterClass
	public void afterClass() {
		extent.flush();
	}
	
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		log.info("*****"+ method.getName() + " Started....!! *********");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		try {
			if(result.getStatus() == ITestResult.FAILURE) {
				log.info(result.getName() + " Test Failed....");
				test.log(Status.FAIL, result.getName() + " Test Failed.... Error Details" + result.getThrowable());
			}
			else if(result.getStatus() == ITestResult.SUCCESS) {
				log.info(result.getName() + " Test Passed....");
				test.log(Status.PASS, result.getName() + " Test Pass");
			}else if(result.getStatus() == ITestResult.SKIP) {
				log.info(result.getName() + " Test Skipped....");
				test.log(Status.SKIP, result.getThrowable());
			}
		}
		catch(Exception e){
			log.error(result.getName() + " throws an exception . Exception details " + e.toString());
		}
		
		finally {
			if(driver != null) {
				driver.quit();
				log.info("*** finally driver quit, After excuting methos: " + result.getMethod().getMethodName());
			}
			log.info(result.getName() + " Finished...!!");
		}
		
	}
	
	
	public static void logInfoExtentReport(String msg) {
		test.log(Status.INFO, msg);
	}
	public static void logWarningExtentReport(String msg) {
		test.log(Status.WARNING, msg);
	}
	public static void logFailExtentReport(String msg) {
		test.log(Status.FAIL, msg);
	}
	public static void logPassExtentReport(String msg) {
		test.log(Status.PASS, msg);
	}
	
	public String TakeScreenShots(String fileName , WebDriver driver) {
		if(driver == null) {
			log.info("driver is null ..not able to capture screenshot");
			return null;
		}
		if(fileName == "") {
			fileName = "Blank";
		}
		File destFile = null;	
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String time = formatter.format(date);
		time = time.replaceAll("/","_").replaceAll(" ", "_").replaceAll(":", "_");
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			destFile = new File(screenShotPath + "/" + fileName+ "_"+ time + ".png");
			FileUtils.copyFile(srcFile, destFile);
			Thread.sleep(2000);
			logInfoExtentReport("Taking Screenshot to " +destFile.toString());
		}catch(Exception e) {
			log.error("Taking screenshot error" + e.toString());
		}
		return destFile.toString();
	}
	
	public static void addScreenShotToReport(WebDriver driver) throws Exception {
		TestBase tb = new TestBase();
		String imagePath = tb.TakeScreenShots("On_Failure_", driver);
		test.addScreenCaptureFromPath(imagePath);
		
	}
}
