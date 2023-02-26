package Listeners;

import Resources.CommonApi;
import Utilities.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends CommonApi implements ITestListener {

    WebDriver driver = null;
    ExtentReports extentReport = ExtentReporter.getExtentReport();
    ExtentTest extentTest;
    ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();


    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getName();
        extentTest = extentReport.createTest(testName + " execution started");
        extentTestThread.set(extentTest);

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String testName = result.getName();
        // extentTest.log(Status.PASS, testName + "Test Passed");
        extentTestThread.get().log(Status.PASS, testName + "Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testMethodName = result.getName();
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String screenshotFilePath = takeScreenshot(testMethodName, driver);
            extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testMethodName);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //extentTest.fail(result.getThrowable());
        extentTestThread.get().fail(result.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

        extentReport.flush();
    }
}
