package com.test.libs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class GmailTest {
	
	WebDriver driver;
	
	@Test
	public void test()
	{
		System.setProperty("webdriver.marionette.firefox","//resources//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://accounts.google.com/ServiceLogin?sacu=1&scc=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&osid=1&service=mail&ss=1&ltmpl=default&rm=false#identifier");
		driver.manage().window().maximize();
		driver.findElement(By.id("Email")).sendKeys("grk541@gmail.com");
		driver.findElement(By.id("next")).click();
		fluentWait(driver, By.id("Passwd"), 1, 5);
		driver.findElement(By.id("Passwd")).sendKeys("Gmail@197@@");
		driver.findElement(By.id("signIn")).click();
		if(selectLink(driver, "red"))
		{
			System.out.println("Element click success");
		}
		else
		{
			System.out.println("Element click failed");
		}
	}
	
	public void fluentWait(WebDriver driver, By by, int poll, int timeOut)
	{
		FluentWait<WebDriver> f = new FluentWait<WebDriver>(driver)
				.withTimeout(timeOut, TimeUnit.SECONDS)
				.pollingEvery(poll, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
		try
		{
			f.until(ExpectedConditions.presenceOfElementLocated(by));
			f.until(ExpectedConditions.elementToBeClickable(by));
		}
		catch(ElementNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean selectLink(WebDriver driver, String linkName)
	{
		String value = "//a[starts-with(.,'" + linkName +"')]";
		boolean flag = false;
		try{
		driver.findElement(By.xpath(value)).click();
		driver.findElement(By.id("gbqfq")).getText().contains("Starred");
		flag=true;
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Element with the link :"+linkName +" ,not found");
		}
		return flag;
	}

}
