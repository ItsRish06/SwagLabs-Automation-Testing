package com.swagLabs.tests;

import com.aventstack.extentreports.Status;
import com.swagLabs.pages.CartPage;
import com.swagLabs.pages.CheckoutPage;
import com.swagLabs.pages.LoginPage;
import commonLibs.implementations.CommonDriver;
import commonLibs.utils.ReportUtils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {
    CommonDriver cmnDriver;
    String url;
    WebDriver driver;
    LoginPage lp;
    String configFileName;
    String reportFilename;
    Properties configProperty;
    String currentWorkingDirectory;
    ReportUtils reportUtils;
    CartPage cp;
    CheckoutPage co;




    @AfterMethod
    public void postTest(ITestResult result){
        String testCaseName = result.getName();
        System.out.println(testCaseName);
        long executionTime = System.currentTimeMillis();

        if(result.getStatus() == ITestResult.FAILURE){
            reportUtils.addTestLog(Status.FAIL,"One or more steps failed.");
        }
    }

    @AfterMethod
    public void tearDown(){
        cmnDriver.closeAllBrowsers();
    }

    @AfterClass
    public void postTearDown(){
        reportUtils.flushReport();
    }


}
