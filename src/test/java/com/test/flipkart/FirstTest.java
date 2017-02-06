package com.test.flipkart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class FirstTest {
	
	WebDriver driver = null;
	
	@Test
	public void methodOne()
	{
		System.setProperty("webdriver.gecko.driver","//resources//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.amazon.in/");
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.cssSelector("#nav-link-yourAccount"))).build().perform();
		driver.findElement(By.xpath("//span[contains(.,'Sign in')]")).click();
		driver.findElement(By.id("ap_email")).sendKeys("grk541@gmail.com");
		driver.findElement(By.id("ap_password")).sendKeys("Amazon@1");
		driver.findElement(By.id("signInSubmit")).click();
		driver.quit();
	}


}
