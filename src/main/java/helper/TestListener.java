package helper;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("The name of the testcase started is :"+iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("The name of the testcase succcess is :"+iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("The name of the testcase failed is :"+iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("The name of the testcase skipped is :"+iTestResult.getName());
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
