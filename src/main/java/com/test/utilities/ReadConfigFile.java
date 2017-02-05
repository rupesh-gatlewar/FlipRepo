package com.test.utilities;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadConfigFile {
	
	static String data = null;
	
	public static String readProperty(String filePath, String value)
	{
		try
		{
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		Properties p = new Properties();
		p.load(fis);
		data = p.getProperty(value);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found : " +filePath);
		}
		catch(IOException e)
		{
			System.out.println("File load error : "+filePath);
		}
		return data;
	}
	
	//test the method
	/*public static void main(String[] args) {
		ReadConfigFile rcf = new ReadConfigFile();
		String s = rcf.readProperty(System.getProperty("user.dir")+"/resources/config.properties", "URL");
		System.out.println(s);
	}*/

}
