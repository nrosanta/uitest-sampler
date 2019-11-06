package com.ui.tests;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviderImpl {
	@DataProvider(name="common-test-data")
	public Object[][] dataProviderMethod(){
		String filename="src/test/resources/test-data/TestData.xlsx";
		String sheetName="Links";
		XSSFSheet excelWSheet = null;
		XSSFWorkbook excelWBook;
	
		try {
			FileInputStream excel = new FileInputStream(filename);
			excelWBook = new XSSFWorkbook(excel);
			excelWSheet = excelWBook.getSheet(sheetName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object[][] obj = new Object[1][1];
		obj[0][0] = getData(excelWSheet);
		
		return obj;
		
	}
	
	public Map<String, Map<String, String>> getData(XSSFSheet worksheet) {
		int colHeaderCount = worksheet.getRow(0).getLastCellNum();
		int rowCount = worksheet.getLastRowNum();
		Map<String, Map<String, String>> dataMap = new HashMap<>();
		List<String> headers = new ArrayList<>();
		
		/*
		 * Get top rows
		 */
		for(int i=0;i<colHeaderCount;i++) {
			headers.add(worksheet.getRow(0).getCell(i).getStringCellValue());
		}
		
		System.out.println("Headers: "+headers);
		
		/*
		 * Get row data
		 */
		//Iterator<Row> rowItr = worksheet.rowIterator();
		for(int i=1;i<rowCount;i++) { //skip 1st row
			//XSSFRow row = (XSSFRow) rowItr.next();
			Map<String, String> rowMap = new HashMap<>();
			for(int j=0;j<headers.size();j++) {
				String cellVal = worksheet.getRow(i).getCell(j).getStringCellValue();
				//System.out.println("Data: "+cellVal);
				rowMap.put(headers.get(j), cellVal);
				
			}
			dataMap.put(worksheet.getRow(i).getCell(0).getStringCellValue(), rowMap);
			
		}
		return dataMap;
		
	}

}
