package com.ui.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
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
		
	}
	
	@AfterSuite
	public void shutdown() {
		driver.quit();
	}
	
	@Test (description = "Validate Home Page Title", priority=1)
	public void validatePageTitle() {
		String url = "http://www.namaonline.org";
		driver.get(url);
		assertion.assertEquals(driver.getTitle(), "testingfly.com","Unexpected page title!");
	}
	
	@Test(description = "Validate Home Page Link", priority=2)
	public void clickHomePage() {
		driver.findElement(By.xpath("//*[@id=\"cssmenu\"]/ul/li[1]/a/span")).click();
		assertion.assertEquals(driver.getCurrentUrl(), "http://www.namaonline.org1");
		
	}
}
