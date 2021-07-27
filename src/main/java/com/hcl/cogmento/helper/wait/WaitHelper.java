package com.hcl.cogmento.helper.wait;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hcl.cogmento.helper.logger.LoggerHelper;

public class WaitHelper {
	
	private static Logger log = LoggerHelper.getLogger(WaitHelper.class);
	private static WebDriver driver;
	
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public static void setImplicitWait(long timeout, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(timeout, unit);
		log.info("Implicit wait has been set to:" +timeout);

	}
	
	/**This method is explicit wait method.If element is visible in the given time,
	 * this method return boolean true otherwise false.
	 * @param element
	 * @param timeOutinSeconds
	 * @return
	 */
	public boolean waitForElementVisible(WebElement element, int timeOutinSeconds){
		boolean display = false;
		try {
			log.info("waiting for :" +element.toString() + " : " +timeOutinSeconds + "seconds to visible ");
			WebDriverWait wait = new WebDriverWait(driver, timeOutinSeconds);
			wait.ignoring(NoSuchElementException.class);
			wait.ignoring(ElementNotVisibleException.class);
			wait.ignoring(StaleElementReferenceException.class);
			wait.ignoring(NoSuchFrameException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			display = element.isDisplayed();
			log.info(element.toString() + " element visible ?" +display);
		}
		catch(Exception e){
			log.info(element.toString() + "element is not visible after " +timeOutinSeconds + "seconds");
		}
		return display;
	}
	
	public boolean waitForElementClickable(WebElement element, int timeOutinSeconds) {
		boolean clickable = false;
		try {
			log.info("waiting for : " +element+ " : "+ timeOutinSeconds + "seconds to be clickable");
			WebDriverWait wait = new WebDriverWait(driver, timeOutinSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			clickable = element.isEnabled();
			log.info(element.toString()+ "element clickable ? " +clickable);
		}catch(Exception e){
			log.info(element.toString() + "element is not clickable after " +timeOutinSeconds + "seconds");
		}
		return clickable;
	}
	
	
}
