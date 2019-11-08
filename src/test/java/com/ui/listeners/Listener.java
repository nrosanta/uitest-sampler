package com.ui.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.ui.tests.TestBase;
import com.ui.utils.Utils;

public class Listener extends TestBase implements ITestListener {

	// WebDriver driver = null;

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("I am in onTestSuccess method ");
		// System.out.println("I am in onTestFailure method "+ driver.getTitle());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// driver = (WebDriver)result.getTestContext().getAttribute("driver");
		System.out.println("I am in onTestFailure method " + driver.getCurrentUrl());
		Utils.takeScreenshot(driver, result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("I am in onTestSkipped method ");
	}

}
