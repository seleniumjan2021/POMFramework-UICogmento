package com.hcl.cogmento.prod.testScript.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.hcl.cogmento.helper.browser.ChromeBrowser;
import com.hcl.cogmento.helper.config.TD;
import com.hcl.cogmento.helper.library.Library;
import com.hcl.cogmento.helper.testbase.TestBase;
import com.hcl.cogmento.helper.util.TestUtil;
import com.hcl.cogmento.helper.wait.WaitHelper;
import com.hcl.cogmento.prod.pageObjects.HomePage;
import com.hcl.cogmento.prod.pageObjects.LoginPage;

public class LoginPageTest extends TestBase {

	SoftAssert soft = new SoftAssert();
	LoginPage login;
	HomePage home;
	Library lib;
	WaitHelper wait;
	WebDriver driver ;
	ChromeBrowser browser = new ChromeBrowser();
	boolean status ;
	
	@Test(priority = 0 , description = "TC001- Verify Launch URL is success")
	public void verifyLaunchURL(){
		test.info("Driver Initialisation in " + this.getClass().getSimpleName() + " class");	
		driver = browser.getChromeDriver();	
		initializeObject();
		login.clearCacheAndMaximizeWindow();
		boolean launchSuccess = login.launch(TD.URL);
		Assert.assertTrue(launchSuccess, "Failed ! Not able to launch Title");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Test(priority =1 , description = "TC002-Login with Valid crendentials")
	public void loginIntoApplicationWithValidCredentials() {
		test.info("Driver Initialisation in " + this.getClass().getSimpleName() + " class");	
		try {
		driver = browser.getChromeDriver();	
		initializeObject();
		login.clearCacheAndMaximizeWindow();
		boolean launchSuccess = login.launch(TD.URL);
		Assert.assertTrue(launchSuccess, "Failed ! Not able to launch Title");
		Thread.sleep(2000);
		status = login.signIntoApplication(TD.uiEmailAddress, TD.uiPassword);
		Assert.assertTrue(status, "Failed !!! User is not able to login into application");
		home.logOutFromApplication();
		status = wait.waitForElementVisible(login.submitBtn, TestUtil.standardWait);
		soft.assertTrue(status, "Failed ! to logout from the application");
		Thread.sleep(2000);
		driver.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Test(priority = 3, description = "TC003 - Login with inValid credentials ")
	public void loginWithApplicationWithInvalidCredentials() {
		test.info("Driver Initialisation in " + this.getClass().getSimpleName() + " class");	
		try {
		driver = browser.getChromeDriver();	
		initializeObject();
		login.clearCacheAndMaximizeWindow();
		boolean launchSuccess = login.launch(TD.URL);
		Assert.assertTrue(launchSuccess, "Failed ! Not able to launch Title");
		Thread.sleep(2000);
		if(wait.waitForElementVisible(login.emailIDField, TestUtil.standardWait)) {
			lib.setValueOnElement(login.emailIDField, TD.uiEmailAddress);
		}
		wait.waitForElementVisible(login.passwordField, TestUtil.standardWait);
		lib.setValueOnElement(login.passwordField, TD.inValidPassword);
		wait.waitForElementClickable(login.submitBtn, TestUtil.standardWait);
		lib.clickOnElement(login.submitBtn);
		if(wait.waitForElementVisible(login.inValidLoginTxt, TestUtil.standardWait)) {
			String invalidText = login.inValidLoginTxt.getText();
			Assert.assertEquals(invalidText, TD.invalidLoginText);
		}
		driver.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 4 , description = "TC004/005 - Invalid credential with one empty field")
	public void loginInApplicationWithEmptyFields()
	{
		test.info("Driver Initialisation in " + this.getClass().getSimpleName() + " class");	
		try {
		driver = browser.getChromeDriver();	
		initializeObject();
		login.clearCacheAndMaximizeWindow();
		boolean launchSuccess = login.launch(TD.URL);
		Assert.assertTrue(launchSuccess, "Failed ! Not able to launch Title");
		Thread.sleep(2000);
		//Entering username /Empty Password
		if(wait.waitForElementVisible(login.emailIDField, TestUtil.standardWait)) {
			lib.setValueOnElement(login.emailIDField, TD.uiEmailAddress);	
		}
		//Submit button
		wait.waitForElementClickable(login.submitBtn, TestUtil.standardWait);
		lib.clickOnElement(login.submitBtn);
		if(wait.waitForElementVisible(login.inValidLoginTxt, TestUtil.standardWait)) {
			String invalidText = login.inValidLoginTxt.getText();
			Assert.assertEquals(invalidText, TD.invalidLoginText);
		}
		//Clearing the Username
		lib.clearValueFromEditField(login.emailIDField);
		//Entering the Password
		wait.waitForElementVisible(login.passwordField, TestUtil.standardWait);
		lib.setValueOnElement(login.passwordField, TD.uiPassword);
		wait.waitForElementClickable(login.submitBtn, TestUtil.standardWait);
		lib.clickOnElement(login.submitBtn);
		if(wait.waitForElementVisible(login.inValidLoginTxt, TestUtil.standardWait)) {
			String invalidText = login.inValidLoginTxt.getText();
			Assert.assertEquals(invalidText, TD.invalidLoginText);
		}
		driver.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void initializeObject() {
		login = new LoginPage(driver);
		lib = new Library(driver);
		wait = new WaitHelper(driver);
		home = new HomePage(driver);
	}
}
