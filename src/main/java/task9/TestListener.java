package task9;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import task9.logger.Log;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.logInfo("Starting test: " + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.logInfo(iTestResult.getName() + "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.logTestFail(iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}