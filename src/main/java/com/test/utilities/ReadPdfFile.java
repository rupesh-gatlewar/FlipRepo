package com.test.utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import org.testng.IReporter;
import org.testng.annotations.Test;

public class ReadPdfFile {
	
	PDFParser parser = null;
	PDFTextStripper strp = null;
	COSDocument cos = null;
	PDDocument pd = null;
	String data = null;
	
	public String readData(String path)
	{
		try
		{
			//For reading from file
			File f = new File(path);
			RandomAccessFile r = new RandomAccessFile(f, "rw");
			parser = new PDFParser(r);
			
			//For reading from URL
			/*URL url = new URL(path);
			RandomAccessBufferedFileInputStream bis = new RandomAccessBufferedFileInputStream(url.openStream());
			parser = new PDFParser(bis);*/
			parser.parse();
			
			cos = parser.getDocument();
			
			pd = new PDDocument(cos);
			System.out.println("TOTAL NO OF PAGES IN PDF FILE : " +pd.getNumberOfPages());
			
			for(int i=1;i<=pd.getNumberOfPages()-82;i++)
			{
				strp = new PDFTextStripper();
				strp.setStartPage(i);
				strp.setEndPage(i);
			}
			
			data = strp.getText(pd);
			System.out.println(data);
			System.out.println(data.length());
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("PDF Document is not found");
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.out.println("Unable to parse the document");
		}
		return data;
	}
	
	//Code for testing the above method
	/*@Test
	public void readMain() {
		try{
		ReadPdfFile r = new ReadPdfFile();
		String s = r.readData(System.getProperty("user.dir")+"/resources//testng_tutorial.pdf");
		Assert.assertEquals(true, s.contains("contact@tutorialspoint.com "));
		}
		catch(NullPointerException e)
		{
			System.out.println("Unable to read the data from PDF");
		}
	}*/

}
