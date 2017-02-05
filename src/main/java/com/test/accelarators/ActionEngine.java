package com.test.accelarators;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionEngine extends TestEngine {

	static Logger log = Logger.getLogger(ActionEngine.class.getName());

	public void handleAlert(WebDriver driver, String action) {
		try {
			Alert a = driver.switchTo().alert();
			if (action.equalsIgnoreCase("accept")) {
				a.accept();
			} else if (action.equalsIgnoreCase("dismiss")) {
				a.dismiss();
			}
		} catch (NoAlertPresentException e) {
			System.out.println("Alert is not present");
			log.error("No alert present error");
		}
	}

	public void fluentWait(WebDriver driver, By by, int poll, int timeOut)
	{
			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
					  .withTimeout(timeOut, TimeUnit.SECONDS)
					  .pollingEvery(poll, TimeUnit.MILLISECONDS)
					  .ignoring(NoSuchElementException.class)
					  .ignoring(TimeoutException.class).ignoring(StaleElementReferenceException.class);
		for(int i=0;i<2;i++)
		{
		try
		{
			fluentWait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
			fluentWait.until(ExpectedConditions.elementToBeClickable(by));
		}
		catch(Exception e)
		{
			System.out.println("Issue while waiting for the element to perform action");
		}
		}
	}

	public void waitForElement(By by, WebDriver driver) {
		try {
			// Wait w = new WebDriverWait(driver, 10);
			// w.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (ElementNotVisibleException e) {
			System.out.println("Element is not visible");
		}
	}

	public boolean actionPerform(WebElement ele) {
		boolean flag = false;

		try {
			Actions a = new Actions(driver);
			a.moveToElement(ele).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		}

		finally {
			if (!flag) {
				// failure
				// write to logs, html and take screenshot
				log.error("Unable to perform action on webelement");
				throw new RuntimeException("Unable to perform click action on element" + ele);
			} else {
				// success and report it on html
				log.info("Successfully performed action on webelement");
			}
		}
	}

	public boolean click(WebElement ele) {
		boolean flag = false;

		try {
			ele.click();
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;
		}

		finally {
			if (!flag) {
				// failure
				// write to logs, html and take screenshot
				log.error("Unable to perform click on webelement");
				throw new RuntimeException("Unable to perform click action on element" + ele);
			} else {
				// success and report it on html
				log.info("Successfully performed click action");
			}
		}

	}

	public boolean sendKeys(WebElement ele, String data) {
		boolean flag = false;

		try {
			ele.sendKeys(data);
			flag = true;
			return flag;
		} catch (Exception e) {
			return flag;
		}

		finally {
			if (!flag) {
				log.error("Unable to perform send keys : " + data);
				throw new RuntimeException("Unable to perform sendkeys action on element :" + ele);
			} else {
				log.info("Send keys performed successfully :" + data);
			}
		}
	}

}
