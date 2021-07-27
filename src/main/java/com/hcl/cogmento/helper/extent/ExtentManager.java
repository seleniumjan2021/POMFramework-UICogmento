package com.hcl.cogmento.helper.extent;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hcl.cogmento.helper.logger.LoggerHelper;
import com.hcl.cogmento.helper.resources.ResourceHelper;
import com.hcl.cogmento.helper.util.TestUtil;


public class ExtentManager {
	
	private static ExtentReports extent;
	private static ExtentHtmlReporter report;
	private static Logger log = LoggerHelper.getLogger(ExtentManager.class);
	
	public static ExtentReports getReportInstance() {
		String reportPath = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String time = formatter.format(date);
		time = time.replaceAll("/","_").replaceAll(" ", "_").replaceAll(":", "_");
		//uiCogmento_02_07_2021_10_00_23.html
		reportPath = ResourceHelper.getReportPath(TestUtil.extentReport, "uiCogmento"+"_"+time+".html");
		log.info("Automation Extent Report located :-->" +reportPath);
		
		report = new ExtentHtmlReporter(reportPath);
		extent = new ExtentReports();
		String reportType = null;
		if(StringUtils.containsIgnoreCase(reportType, "UICogmento")) {
			report.config().setReportName("UI Cogmento Automation Report");
			report.config().setDocumentTitle("UI Cogmento Regression Test");
			extent.setSystemInfo("Browser Name", TestUtil.browserName);
			extent.setSystemInfo("Version", TestUtil.uiCogmentoVerion);
		}else if(StringUtils.containsIgnoreCase(reportType, "UICogmento Product")) {
			report.config().setReportName("UI Cogmento Product Automation Report");
			report.config().setDocumentTitle("UI Cogmento Product Regression Test");
			extent.setSystemInfo("Browser Name", TestUtil.browserName);
			extent.setSystemInfo("Version", TestUtil.uiCogmentoVerion);
		}else {
			report.config().setReportName("UI Automation Report");
			report.config().setDocumentTitle("UI Regression Test");
			
		}
		
		extent.setSystemInfo("Environment", TestUtil.env);
		extent.setSystemInfo("Author", "Ashutosh Kumar");
		extent.setSystemInfo("Author Role", "Automation Lead");
		report.config().setTheme(Theme.DARK);
		report.config().setEncoding("utf-8");
		extent.attachReporter(report);
		
		return extent;
			
	}
}
