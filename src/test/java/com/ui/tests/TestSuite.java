package com.ui.tests;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ui.core.DriverSetup;

public class TestSuite {
	WebDriver driver;
	SoftAssert assertion = new SoftAssert();
	
	@BeforeSuite
	public void loadDriver() {
		driver = new DriverSetup().getDriver();
		//Reporter.getCurrentTestResult().getTestContext().setAttribute("sheetName", "Links");
	}
	
	@AfterSuite
	public void shutdown() {
		driver.quit();
	}
	
	@Test(priority=3)
	public void setDataSourceGrp1() {
		//System.out.println("data source*** "+Reporter.getCurrentTestResult().getTestContext().getAttribute("sheetName"));
		Reporter.getCurrentTestResult().getTestContext().setAttribute("sheetName", "Links");
	}
	
	@Test(priority=4)
	public void setDataSourceGrp2() {
		//System.out.println("data source*** "+Reporter.getCurrentTestResult().getTestContext().getAttribute("sheetName"));
		Reporter.getCurrentTestResult().getTestContext().setAttribute("sheetName", "Menu");
	}
	
	@Test (dependsOnMethods="setDataSourceGrp1", dataProvider="common-test-data", dataProviderClass=com.ui.tests.DataProviderImpl.class, description = "Validate Page Titles", priority=1)
	public void validatePageTitle(Map<String, Map<String, String>> dataMap) {
		//ITestContext ctx = Reporter.getCurrentTestResult().getTestContext();
		//ctx.setAttribute("test", "01");
		//Reporter.getCurrentTestResult().getTestContext().setAttribute("sheetName", "Links");
		System.out.println("context attributes*** "+Reporter.getCurrentTestResult().getTestContext().getAttribute("sheetName").toString());
		for(String key : dataMap.keySet()) {
			String url = dataMap.get(key).get("URL");
			String title = dataMap.get(key).get("Title");
			driver.get(url);
			assertion.assertEquals(driver.getTitle(), title,"Unexpected page title for page "+key);
		}
		assertion.assertAll();
		
	}
	
	@Test(dependsOnMethods="setDataSourceGrp2", dataProvider="common-test-data", dataProviderClass=com.ui.tests.DataProviderImpl.class, description = "Validate Page Links", priority=2)
	public void validateMenuLinks(Map<String, Map<String, String>> dataMap) {
		//Reporter.getCurrentTestResult().getTestContext().setAttribute("sheetName", "Menu");
		System.out.println("context attributes*** "+Reporter.getCurrentTestResult().getTestContext().getAttribute("sheetName").toString());
		for(String key : dataMap.keySet()) {
			String url = dataMap.get(key).get("URL");
			driver.get(url);
			assertion.assertEquals(driver.getCurrentUrl(), url, "Unexpected URL!");
		}
		assertion.assertAll();
		
	}
}
