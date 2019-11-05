package com.ui.tests;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviderImpl {
	@DataProvider(name="data-provider")
	public Object[][] dataProviderMethod(){
		String filename="src/test/resources/test-data/TestData.xlsx";
		String sheetName="Links";
		XSSFSheet excelWSheet;
		XSSFWorkbook excelWBook;
		XSSFCell cell;
		XSSFRow row;
		
		
		try {
			FileInputStream excel = new FileInputStream(filename);
			excelWBook = new XSSFWorkbook(excel);
			excelWSheet = excelWBook.getSheet(sheetName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}

}
