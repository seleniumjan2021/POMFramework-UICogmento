package com.hcl.cogmento.helper.listener;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.hcl.cogmento.helper.extent.ExtentManager;
import com.hcl.cogmento.helper.testbase.TestBase;

public class ExtentListener extends TestBase implements ITestListener {
   
	ExtentReports extentListener = ExtentManager.getReportInstance();
	ExtentTest testListener;
	private static ThreadLocal<ExtentTest> extentTestListener = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		testListener = extentListener.createTest(result.getMethod().getMethodName());
		extentTestListener.set(testListener);
		extentTestListener.get().log(Status.INFO, result.getName() + " started..");
		Reporter.log(result.getMethod().getMethodName() + " Started...");
		
	}

	public void onTestSuccess(ITestResult result) {
		extentTestListener.get().log(Status.PASS, result.getTestName() + " Passed...");
		Reporter.log(result.getMethod().getMethodName() + " Test Passed...");
		
	}

	public void onTestFailure(ITestResult result) {
		
		extentTestListener.get().log(Status.FAIL, result.getTestName() + " Test Failed....");
		extentTestListener.get().error("Error details : " + result.getThrowable());
		
		try {
			extentTestListener.get().addScreenCaptureFromPath(TakeScreenShots(result.getMethod().getMethodName(), driver));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		Reporter.log(result.getMethod().getMethodName() + " Test Failed..." +result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		extentTestListener.get().log(Status.SKIP, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName() + " Test Skipped. Error is " + result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		extentListener.flush();
		Reporter.log(context.getName() + "Test Finished");
		
	}

}
