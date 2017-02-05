package com.test.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	XSSFWorkbook workbook = null;
	XSSFSheet sheet = null;
	XSSFRow row = null;
	XSSFCell cell = null;
	FileOutputStream fos = null;
	String path = null;
	
	public ExcelReader(String path)
	{
		this.path = path;
		try
		{
			File f = new File(path);
			FileInputStream fis = new FileInputStream(f);
			workbook = new XSSFWorkbook(fis);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch(IOException e)
		{
			System.out.println("Wrong location of the file");
		}
	}
	
	public String readData(String sheetName, int rowNum, int colNum)
	{
		String cellValue = null;
		int sheetNum = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(sheetNum);
		
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		if(cell.getCellType() == cell.CELL_TYPE_NUMERIC)
		{
			cellValue = String.valueOf(cell.getNumericCellValue());
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
		           // format in form of M/D/YY
				  double d = cell.getNumericCellValue();
				  Calendar cal =Calendar.getInstance();
				  cal.setTime(HSSFDateUtil.getJavaDate(d));
				  System.out.println(cal);
				  cellValue =
		             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
				  cellValue = cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                      cal.get(Calendar.MONTH)+1 + "/" + 
		                      cellValue;
		           
		         }
		}
		
		return cellValue;
	}
	
	public void writeData(String sheetName, int rowNum, int colNum, String data)
	{
		try{
		int sheetNum = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(sheetNum);
		
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		if (cell == null)
	        cell = row.createCell(colNum);
		
		cell.setCellValue(data);
		fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("file not found");
		}
		catch(IOException e)
		{
			System.out.println("IO error");
		}
	}
	
	//Code to test the above method
	/*public static void main(String[] args) {
		ExcelReader e = new ExcelReader("/Users/rupesh/Desktop/excelOne.xlsx");
		//String value = e.readData("one", 2, 0);
		//System.out.println(value);
		e.writeData("one", 1, 3, "1/1/1990");
	}*/

}
