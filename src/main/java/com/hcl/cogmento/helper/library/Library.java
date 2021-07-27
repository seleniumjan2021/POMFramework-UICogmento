package com.hcl.cogmento.helper.library;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hcl.cogmento.helper.logger.LoggerHelper;
import com.hcl.cogmento.helper.testbase.TestBase;
import com.hcl.cogmento.helper.wait.WaitHelper;

public class Library {

	private static Logger log = LoggerHelper.getLogger(Library.class);
	private static WebDriver driver;

	public Library(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param element
	 * @throws Exception
	 */
	public void clickOnElement(WebElement element) throws Exception {
		try {
			element.click();
			log.info(element.toString() + "clicked");
			TestBase.logInfoExtentReport("In Class" + getClass().getSimpleName() + " " + element.toString());
		} catch (Exception e) {
			log.info(element.toString() + " element is not clicked. Error : " + e.toString());
			TestBase.logFailExtentReport("In Class" + getClass().getSimpleName() + " " + element.toString()
					+ " element is not clicked. Error :" + e.toString());
			TestBase.addScreenShotToReport(driver);
		}
	}

	/**
	 * This method will set the value in any edit field
	 * @param element
	 * @param vText
	 * @throws Exception 
	 */
	public void setValueOnElement(WebElement element , String vText) throws Exception {
		try 
		{
			element.sendKeys(vText);
			log.info(vText + " value set to " + element.toString());
			TestBase.logInfoExtentReport(vText + " value set to " + element.toString());
		}
		catch (Exception e) {
			log.info(vText + " value is not set to " + element.toString());
			TestBase.logFailExtentReport(vText + " value is not set to " + element.toString());
			TestBase.addScreenShotToReport(driver);
		}
	}
	
	/**
	 * @param element
	 * @throws Exception
	 */
	public void clearValueFromEditField(WebElement element) throws Exception {
		try 
		{
			element.clear();
			log.info(element.toString() + "value is cleared");
			TestBase.logInfoExtentReport(element.toString() + "value is cleared");
		} catch (Exception e) {
			log.info(element.toString() + "value is not cleared " + e.toString());
			TestBase.logFailExtentReport(element.toString() + "value is not cleared " + e.toString());
			TestBase.addScreenShotToReport(driver);
		}
	}
}
