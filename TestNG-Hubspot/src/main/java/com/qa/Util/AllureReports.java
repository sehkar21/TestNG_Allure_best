package com.qa.Util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.Base.BasePage;

import io.qameta.allure.Attachment;

public class AllureReports implements ITestListener {

	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}

	@Override // start method
	public void onStart(ITestContext context) {
		System.out.println("I am in onStart method " + context.getName());

	}

	@Override // start Test
	public void onTestStart(ITestResult result) {
		System.out.println("Iam on Test Start Method" + getTestMethodName(result) + "Start");
	}

	@Override // Test case success
	public void onTestSuccess(ITestResult result) {
		System.out.println("Iam on Test SucessMethod" + getTestMethodName(result) + "success");

	}

	@Override // Test Case Failure
	public void onTestFailure(ITestResult result) {
		System.out.println("Iam on Test FailureMethod" + getTestMethodName(result) + "Failed");
		Object test = result.getInstance();
		WebDriver driver = BasePage.getDriver();
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot Captured for failure test case" + getTestMethodName(result));
			SaveFailureScreenShot(driver);
		}
		saveTextLog(getTestMethodName(result) + "Test case Failed and Screenshot Taken");

	}

	// Method for Logs associated with Test case Failure
	@Attachment(value = "{0}", type = "text/plain")
	private static String saveTextLog(String message) {
		return message;

	}

	@Attachment
	public byte[] SaveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// method for Screenshot associated with Test case Failure
	@Attachment(value = "pagescreenshot", type = "image/png")
	private byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

	}

	@Override // Test case Skipped
	public void onTestSkipped(ITestResult result) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(result) + " skipped");

	}

	@Override // Test Case Failed but within Success Percentage
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(result));

	}

	@Override // Finish Method
	public void onFinish(ITestContext context) {
		System.out.println("I am in onFinish method " + context.getName());

	}

}
