package util;

import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterNGAventstack.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		System.out.println("Starting " + result.getMethod().getMethodName() + " test");
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) { // ITestResult is an argument; result is a result of your test
		extentTest.get().fail(result.getThrowable());
		String testMethodName = result.getMethod().getMethodName();

		//for parallel execution we need to separate drivers for each test > so reports and screenshots do not interfere with each other
	    WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
		}

		try {
			//BasePage.getScreenshot(testMethodName);
			String image=BasePage.getScreenshot(testMethodName, driver);
			extentTest.get().addScreenCaptureFromPath(image, testMethodName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onStart(ITestContext context) {  // before starting any test case
	}

	public void onFinish(ITestContext context) {   // will be executed after each and every test
		extent.flush();
	}
}