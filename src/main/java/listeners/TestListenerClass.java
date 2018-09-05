package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListenerClass implements ITestListener {
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
