package org.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    int count = 0;
    int maxTry = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if (count<maxTry){
            count++;
            return true;
        }

        return false;
    }
}
