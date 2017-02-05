package com.test.flipkart;

import org.testng.annotations.Test;

import com.test.libs.AppLogin;

public class LoginTest extends AppLogin{
	
	@Test
	public void loginTest() throws Throwable
	{
			//testFlow();
		loginAction();
	}
	
	/*@Test
	public void loginTest2() throws Throwable
	{
			//testFlow();
		loginAction();
	}
	
	@Test
	public void loginTest3() throws Throwable
	{
			//testFlow();
		loginAction();
	}*/
	
}
