package listeners;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListenerClass implements ITestListener {

    @Attachment
    public void captureScreenshot(WebDriver d) throws IOException {
        System.out.println("Trying to capture Screenshot..");
        File scr = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
        System.out.println("Trying to save file to ..." + "screenshot_" + d.getTitle().toLowerCase());
        FileUtils.copyFile(scr, new File("screenshot_" + d.getTitle().toLowerCase()));
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
            try {
                captureScreenshot((WebDriver) webDriverAttribute);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
