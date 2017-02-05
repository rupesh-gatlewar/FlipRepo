package com.test.accelarators;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.test.utilities.ReadConfigFile;

public class TestEngine {
	
	public WebDriver driver = null;
	public String propFile = System.getProperty("user.dir")+"/resources/config.properties";
	
	@Parameters("browser")
	@BeforeMethod
	public void beforeTest(String browser)
	{
		if(ReadConfigFile.readProperty(propFile, "LOCATION").equalsIgnoreCase("local"))
		{
		if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.marionette.firefox","//resources//geckodriver.exe");
			this.driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			
		}
		}
		else if(ReadConfigFile.readProperty(propFile, "LOCATION").equalsIgnoreCase("grid"))
		{
			if(browser.equalsIgnoreCase("firefox"))
			{
				try{
				DesiredCapabilities dc = new DesiredCapabilities();
				dc.setBrowserName("firefox");
				this.driver = new RemoteWebDriver(new URL(ReadConfigFile.readProperty(propFile, "NODE")),dc);
				}
				catch(MalformedURLException e)
				{
					System.out.println("Node URL is invalid");
				}
			}
			else if(browser.equalsIgnoreCase("chrome"))
			{
				
			}
		}
		
		else if(ReadConfigFile.readProperty(propFile, "LOCATION").equalsIgnoreCase("saucelabs"))
		{
			final String URL = "https://" + ReadConfigFile.readProperty(propFile, "SAUCELABS_USERNAME") + ":" + ReadConfigFile.readProperty(propFile, "SAUCELABS_AUTOMATE_KEY") + "@hub-cloud.browserstack.com/wd/hub";

			if(browser.equalsIgnoreCase("firefox"))
			{
				try
				{
					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setCapability("browserstack.local", "true");
					caps.setCapability("browser", "IE");
					caps.setCapability("browser_version", "7.0");
					caps.setCapability("os", "Windows");
					caps.setCapability("os_version", "XP");
					caps.setCapability("browserstack.debug", "true");
					this.driver = new RemoteWebDriver(new URL(URL), caps);
				}
				catch(MalformedURLException e)
				{
					System.out.println("Issue with the URL");
				}
			}
			else if(browser.equalsIgnoreCase("chrome"))
			{
				
			}
		}
		
		this.driver.get(ReadConfigFile.readProperty(propFile, "URL"));
		this.driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
	}
	
	public WebDriver getDriver()
	{
		return this.driver;
	}
	
	@AfterMethod
	public void afterTest()
	{
		driver.close();
		driver.quit();
	}

}
