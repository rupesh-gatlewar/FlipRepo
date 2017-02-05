package com.test.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.test.accelarators.TestEngine;

public class ScreenshotListener extends TestListenerAdapter{
	
	private boolean createFile(File file)
	{
		boolean flag=false;
		if(file.exists())
		{
			flag = true;
		}
		else
		{
			File parent = new File(file.getParent());
			if(parent.exists()||parent.mkdirs())
			{
				try
				{
				flag = file.createNewFile();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		}
		return flag;
	}
	
	private void takeScreenShot(WebDriver driver, File screenshot) throws FileNotFoundException
	{
		try
		{
		FileOutputStream fis = new FileOutputStream(screenshot);
		fis.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
		fis.close();
		}
		catch(Exception e)
		{
			System.out.println("unable to write to file");
		}
	}
	
	 public void onTestFailure(ITestResult failingTest)
	 {
		 Object instance = failingTest.getInstance();
		 try
		 {
			 String path=System.getProperty("user.dir")+"/screenshot";
			 String absolutePath=path +File.separator +System.currentTimeMillis() +"_" +failingTest.getName() +"_" +".png";
			 File f = new File(absolutePath);
			 if(createFile(f))
			 {
				 try
				 {
				 takeScreenShot(((TestEngine)instance).getDriver(),f);
				 System.out.println("Writing to the file successfull");
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
			 }
			 else
			 {
				 System.out.println("failed to create file");
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	 }

}
