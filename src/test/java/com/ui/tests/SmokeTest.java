package com.ui.tests;

import java.util.Map;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

	@Test(priority = 3)
	public void setDataSourceGrp1() {
		Reporter.getCurrentTestResult().getTestContext().setAttribute("sheetName", "Links");
	}

	@Test(priority = 4)
	public void setDataSourceGrp2() {
		Reporter.getCurrentTestResult().getTestContext().setAttribute("sheetName", "Menu");
	}

	@Test(dependsOnMethods = "setDataSourceGrp1", dataProvider = "common-test-data", dataProviderClass = com.ui.tests.DataProviderImpl.class, description = "Validate Page Titles", priority = 1)
	public void validatePageTitle(Map<String, Map<String, String>> dataMap) {
		for (String key : dataMap.keySet()) {
			String url = dataMap.get(key).get("URL");
			String title = dataMap.get(key).get("Title");
			driver.get(url);
			assertion.assertEquals(driver.getTitle(), title, "Unexpected page title for page " + key);
		}
		assertion.assertAll();

	}

	@Test(dependsOnMethods = "setDataSourceGrp2", dataProvider = "common-test-data", dataProviderClass = com.ui.tests.DataProviderImpl.class, description = "Validate Page Links", priority = 2)
	public void validateMenuLinks(Map<String, Map<String, String>> dataMap) {
		for (String key : dataMap.keySet()) {
			String url = dataMap.get(key).get("URL");
			driver.get(url);
			assertion.assertEquals(driver.getCurrentUrl(), url, "Unexpected URL!");
		}
		assertion.assertAll();

	}
}
