package com.ui.tests;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.ui.listeners.Listener.class)
public class SmokeTest extends TestBase {

	@Test(priority = 3)
	public void setDataSourceGrp1() {
		Reporter.getCurrentTestResult().getTestContext().setAttribute("sheetName", "Links");
	}

	@Test(priority = 4)
	public void setDataSourceGrp2() {
		Reporter.getCurrentTestResult().getTestContext().setAttribute("sheetName", "Menu");
	}

	@Test(dependsOnMethods = "setDataSourceGrp1", dataProvider = "common-test-data", dataProviderClass = com.ui.tests.DataProviderImpl.class, description = "Validate Page Titles", priority = 1)
	public void validatePageTitle(Map<String, Map<String, String>> dataMap) throws IOException {
		System.out.println("Running page title validations...");
		for (String key : dataMap.keySet()) {
			String url = dataMap.get(key).get("URL");
			String title = dataMap.get(key).get("Title");
			driver.get(url);

			assertion.assertEquals(driver.getTitle(), title, "Unexpected page title for page " + key);
			
			//if (!driver.getTitle().equals(title)) {
				
			//}
			assertion.assertAll();
		}
		System.out.println("Title Validations complete.");

	}

	@Test(dependsOnMethods = "setDataSourceGrp2", dataProvider = "common-test-data", dataProviderClass = com.ui.tests.DataProviderImpl.class, description = "Validate Page Links", priority = 2)
	public void validateMenuLinks(Map<String, Map<String, String>> dataMap) throws IOException {
		System.out.println("Keys*** "+dataMap.keySet());
		
		for (String key : dataMap.keySet()) {
			String url = dataMap.get(key).get("URL");
			driver.get(url);
			assertion.assertEquals(driver.getCurrentUrl(), url, "Unexpected URL!");
			
			System.out.println("No issues with About*****"+key);
			if(key.equals("About")) {
				
				String act = driver.findElement(By.xpath("/html/body/form/div[3]/div[3]/div[2]/h1")).getText();
				assertion.assertEquals(act, "About NAMA1", "Unexpected heading!");
				
			}
			
			//if (!driver.getCurrentUrl().equals(url)) {
				//Utils.takeScreenshot(driver, key);
				
			//}
			//assertion.assertAll();
			
		}
		
		System.out.println("Menu validtions complete.");
		

	}
}
