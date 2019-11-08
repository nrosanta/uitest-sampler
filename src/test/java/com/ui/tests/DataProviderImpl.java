package com.ui.tests;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.ui.utils.Config;

public class DataProviderImpl {
	String filename=Config.getProp("testfile-path");
	String sheetName="Links";//default sheet
	@DataProvider(name="common-test-data")
	public Object[][] dataProviderMethod(ITestContext testContext){

		XSSFSheet excelWSheet = null;
		XSSFWorkbook excelWBook;
		//System.out.println("context attributes*** "+testContext.getAttribute("sheetName").toString());
		sheetName = testContext.getAttribute("sheetName").toString();
	
		try {
			FileInputStream excel = new FileInputStream(filename);
			excelWBook = new XSSFWorkbook(excel);
			excelWSheet = excelWBook.getSheet(sheetName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Object[][] {{getData(excelWSheet)}};
		
	}
	
	public Map<String, Map<String, String>> getData(XSSFSheet worksheet) {
		int colHeaderCount = worksheet.getRow(0).getLastCellNum();
		int rowCount = worksheet.getLastRowNum();
		Map<String, Map<String, String>> dataMap = new HashMap<>();
		List<String> headers = new ArrayList<>();
		
		try {
			/*
			 * Get top rows
			 */
			for(int i=0;i<colHeaderCount;i++) {
				headers.add(worksheet.getRow(0).getCell(i).getStringCellValue());
			}
			
			
			/*
			 * Get row data
			 */
			for(int i=1;i<rowCount;i++) { //skip 1st row
				Map<String, String> rowMap = new HashMap<>();
				for(int j=0;j<headers.size();j++) {
					String cellVal = worksheet.getRow(i).getCell(j)==null?"":worksheet.getRow(i).getCell(j).getStringCellValue();
					rowMap.put(headers.get(j), cellVal);
				}
				dataMap.put(worksheet.getRow(i).getCell(0).getStringCellValue(), rowMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
		
	}

}
