package com.hcl.cogmento.helper.listener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.hcl.cogmento.helper.extent.ExtentManager;
import com.hcl.cogmento.helper.logger.LoggerHelper;

public class RetryAnalyzer implements IRetryAnalyzer {

	int count =0;
	int retryCount = 3;
	
	private static Logger log = LoggerHelper.getLogger(RetryAnalyzer.class);
	
	
	public boolean retry(ITestResult result) {
		if(count <retryCount) {
			count++;
			log.info("Retrying Test " + result.getName()+ "with status " +getResultStatusName(result.getStatus()));
			return true;
		}
		return false;
	}

	
	private String getResultStatusName(int status) {
		String resultName = null;
		if(status ==1) {
			resultName = "SUCCESS";
		}
		if(status ==2) {
			resultName = "FAILURE";
		}
		if(status ==3) {
			resultName = "SKIP";
		}
	
		return resultName;	
	}
	
	
}
