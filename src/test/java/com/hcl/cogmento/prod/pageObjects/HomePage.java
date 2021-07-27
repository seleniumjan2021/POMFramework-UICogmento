package com.hcl.cogmento.prod.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hcl.cogmento.helper.library.Library;
import com.hcl.cogmento.helper.logger.LoggerHelper;
import com.hcl.cogmento.helper.util.TestUtil;
import com.hcl.cogmento.helper.wait.WaitHelper;

public class HomePage {

	private static Logger log = LoggerHelper.getLogger(HomePage.class);
	private static WebDriver driver;
	Library lib;
	WaitHelper wait;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this); 
	    lib = new Library(driver);
		wait = new WaitHelper(driver);
	}
	
	@FindBy(xpath = "//i[@class= 'settings icon']/parent::div")
	public WebElement settingIcon;
	
	@FindBy(xpath = "//*[contains(text(),'Log Out')]")
	public WebElement logOutBtn;
	
	public boolean logOutFromApplication() throws Exception {
		if(wait.waitForElementVisible(settingIcon, TestUtil.standardWait)) {
			lib.clickOnElement(settingIcon);
			Thread.sleep(1000);
			lib.clickOnElement(logOutBtn);
			return true;
		}
		return false;
	}
	
}
