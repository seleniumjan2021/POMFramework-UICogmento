package com.hcl.cogmento.prod.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hcl.cogmento.helper.config.TD;
import com.hcl.cogmento.helper.library.Library;
import com.hcl.cogmento.helper.logger.LoggerHelper;
import com.hcl.cogmento.helper.util.TestUtil;
import com.hcl.cogmento.helper.wait.WaitHelper;

public class LoginPage {
	private static Logger log = LoggerHelper.getLogger(LoginPage.class);
	private static WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this); 
	}
	//Here contains all the locator that belongs to LoginPage
	@FindBy(name = "email")
	public WebElement emailIDField;

	@FindBy(name = "password")
	public WebElement passwordField;
	
	@FindBy(xpath = "//*[text()='Login']")
	public WebElement submitBtn;
	
	@FindBy(xpath = "//*[contains(text(),'Forgot')]")
	public WebElement forgotPasswordLink;
	
	@FindBy(xpath = "//*[contains(text(),'Invalid login')]")
	public WebElement inValidLoginTxt;
	
	//Purpose: Is to allow user to enter into the application from LoginPage.
	
	public boolean signIntoApplication(String username , String password) throws Exception
	{ 
		Library lib = new Library(driver);
		WaitHelper wait = new WaitHelper(driver);
		if(wait.waitForElementVisible(emailIDField, TestUtil.standardWait)) {
			lib.setValueOnElement(emailIDField, username);
		}
		wait.waitForElementVisible(passwordField, TestUtil.standardWait);
		lib.setValueOnElement(passwordField, password);
		wait.waitForElementClickable(submitBtn, TestUtil.standardWait);
		lib.clickOnElement(submitBtn);
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'"+TD.fullName+"')]"));
		if(wait.waitForElementVisible(element, TestUtil.standardWait)) {
			return true;
		}
		
		return false;	
	}
	
	
	public void clearCacheAndMaximizeWindow() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public boolean launch(String URL) {		
		driver.get(URL);
		if(driver.getTitle().equals(TD.pageTitle)){
			return true;
		}
		return false;
	}
}
