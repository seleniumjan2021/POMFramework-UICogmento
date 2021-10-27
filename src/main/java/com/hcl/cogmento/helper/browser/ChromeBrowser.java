package com.hcl.cogmento.helper.browser;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.hcl.cogmento.helper.logger.LoggerHelper;
import com.hcl.cogmento.helper.resources.ResourceHelper;

public class ChromeBrowser {

	@SuppressWarnings("unused")
	private static Logger log = LoggerHelper.getLogger(ChromeBrowser.class);
	public static WebDriver driver = null;
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--test-type");
		option.addArguments("--disable-popup-blocking");
		option.addArguments("allow-file-access-from-files");
	    option.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		//To Enable the javaScript using desiredcap
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		option.setCapability(ChromeOptions.CAPABILITY, chrome);
		return option;	
	}


	public WebDriver getChromeDriver() {
		if(System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("driver/chromedriver"));
			return new ChromeDriver(getChromeOptions());
		}else if(System.getProperty("os.name").toLowerCase().contains("window")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("driver/chromedriver.exe"));
			return new ChromeDriver(getChromeOptions());
		}
		return null;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		ChromeBrowser browser = new ChromeBrowser();
		driver = browser.getChromeDriver();
		driver.manage().deleteAllCookies();
		driver.navigate().to("https://www.facebook.com");
		
	}
}
