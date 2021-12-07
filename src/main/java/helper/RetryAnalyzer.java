package helper;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int retryCounter = 1;
    int retryMaxLimit = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCounter < retryMaxLimit) {
            retryCounter++;
            return true;
        }
        return false;
    }
}
