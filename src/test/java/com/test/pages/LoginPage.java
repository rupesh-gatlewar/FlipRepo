package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(id="ap_email")
	public WebElement userTextBox;
	
	@FindBy(id="ap_password")
	public WebElement passTextBox;
	
	@FindBy(id="signInSubmit")
	public WebElement signInButton;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
