package listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListenerClass implements ITestListener {

    @Attachment
    public byte[] captureScreenshot(WebDriver d) {
        return ((TakesScreenshot) d).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test has been started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test success");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test FAILURE");
        Object webDriverAttribute = iTestResult.getTestContext().getAttribute("WebDriver");
        if (webDriverAttribute instanceof WebDriver) {
            System.out.println("Screesnshot captured for test case:" + iTestResult.getMethod().getMethodName());
            captureScreenshot((WebDriver) webDriverAttribute);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Failed but within success percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Start from ITESTContext");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Finish from ITESTContext");
    }
}
