package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports report=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extenttest=new ThreadLocal();
	@Override
	public void onTestStart(ITestResult result)
	{
		test=report.createTest(result.getMethod().getMethodName());
		extenttest.set(test);
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		extenttest.get().log(Status.PASS,"Test passed");
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		extenttest.get().fail(result.getThrowable());
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String path=null;
		try {
			path=getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extenttest.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
	}
	@Override
	public void onFinish(ITestContext context)
	{
		report.flush();
	}

}
