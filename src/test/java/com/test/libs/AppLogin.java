package com.test.libs;


import com.test.accelarators.ActionEngine;
import com.test.pages.HomePage;
import com.test.pages.LoginPage;
import com.test.pages.MainPage;
import com.test.utilities.ReadConfigFile;

public class AppLogin extends ActionEngine{
	
	public void loginAction()
	{
		HomePage hp = new HomePage(driver);
		actionPerform(hp.signLink);
		click(hp.signInButton);
		LoginPage lp = new LoginPage(driver);
		sendKeys(lp.userTextBox, ReadConfigFile.readProperty(propFile, "USERNAME"));
		sendKeys(lp.passTextBox, ReadConfigFile.readProperty(propFile, "PASS"));
		click(lp.signInButton);
	}
	
	public void searchItem()
	{
		MainPage mp = new MainPage(driver);
		sendKeys(mp.textBox, "wildcraft bags");
		click(mp.searchButton);
	}
	
	public void testFlow()
	{
		HomePage hp = new HomePage(driver);
		hp.logo.click();
	}
	
}
