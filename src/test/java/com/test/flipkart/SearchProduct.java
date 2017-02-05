package com.test.flipkart;

import org.testng.annotations.Test;

import com.test.libs.AppLogin;
import com.test.utilities.Retry;

public class SearchProduct extends AppLogin{
	
	@Test(retryAnalyzer=Retry.class)
	public void searchProduct()
	{
		loginAction();
		searchItem();
	}

}
