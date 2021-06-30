package com.hcl.cogmento.helper.javaScript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hcl.cogmento.helper.logger.LoggerHelper;

public class JavaScriptHelper {

	private static Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	private static WebDriver driver;
	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
	}
	/** This method will execute java script
	 * @param script
	 * @return
	 */
	public Object executeScript(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(script);
	}
	/** This method will execute java script with multiple arguments
	 * @param script
	 * @param args
	 * @return
	 */
	public Object executeScript(String script , Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(script, args);
	}
	
	/**
	 * This method will scroll till element 
	 * @param element
	 * @return
	 */
	public boolean scrollToElement(WebElement element) {
		executeScript("window.scrollTo(arguments[0].arguments[1])", element.getLocation().x, element.getLocation().y);
		log.info("scroll to WebElement " +element.toString()+ ". Is this element display? " +element.isDisplayed());
		return element.isDisplayed();
	}
	
	/**
	 * Scroll till element and click
	 * @param element
	 */
	public void scrollToElementAndClick(WebElement element) {
		boolean isElementDisplay = scrollToElement(element);
		if(isElementDisplay) {
			element.click();
			log.info("element is clicked: " +element.toString());
		}else {
			log.info("Failed to click on element : "+element.toString());
		}
	}
	public void scrollDownVertically() {
		executeScript("window.scrollTO(0,document.body.scrollHeight)");
		log.info("scrolling down verically...");
	}
	public void scrollUpVertically() {
		executeScript("window.scrollTO(0,-document.body.scrollHeight)");
		log.info("scrolling up verically...");
	}
	public void scrollDownByPixel(int pixel) {
		executeScript("window.scrollBy(0," +pixel+ ")");
	}
	public void scrollUpByPixel(int pixel) {
		executeScript("window.scrollBy(0,-" +pixel+ ")");
	}
	
	//Write two method for Zoom in by percentage(100 ,60)
	
	public void clickElement(WebElement element) {
		executeScript("arguments[0].click();", element);
	}
}
