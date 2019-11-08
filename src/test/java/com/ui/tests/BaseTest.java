package com.ui.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.ui.core.DriverSetup;
import com.ui.utils.Config;

public class BaseTest {

	WebDriver driver;
	SoftAssert assertion = new SoftAssert();

	@BeforeSuite
	public void loadDriver() throws IOException {
		Config.loadProps();
		driver = new DriverSetup().getDriver();

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
