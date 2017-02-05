package com.test.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

public class BaseTest {
	
	WebDriver driver;
	
	@Test
	public void test()
	{
		try{
		System.setProperty("webdriver.marionette.firefox","/resources/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.google.co.in");
		Pattern p = new Pattern("/Users/rupesh/Desktop/sign.png");
		//driver.manage().window().maximize();
		Screen s = new Screen();
		//s.wait("/Users/rupesh/Documents/Framework/flipkart/resources/ImageOne.png",3);
		s.click(p);
		}
		catch(FindFailed e)
		{
			e.printStackTrace();
		}
	}

}
