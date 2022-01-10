package com.swagLabs.tests;

import com.aventstack.extentreports.Status;
import com.swagLabs.pages.LoginPage;
import commonLibs.implementations.CommonDriver;
import commonLibs.utils.ReportUtils;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest{

    @BeforeClass
    public void reportSetup(){
        currentWorkingDirectory = System.getProperty("user.dir");
        reportFilename =currentWorkingDirectory+"/reports/swagLabsTestReport-Login.html";
        reportUtils = new ReportUtils(reportFilename);
    }


    @BeforeMethod
    public void setup() throws Exception {
        url = "https://www.saucedemo.com/";
        String browserType = "chrome";
        cmnDriver = new CommonDriver(browserType);
        driver = cmnDriver.getDriver();
        lp = new LoginPage(driver);
        cmnDriver.navigateToUrl(url);

    }

    @Parameters({"correct_username","correct_password"})
    @Test
    public void loginWithCorrectCreds(String username,String password){

        reportUtils.createATestCase("Verify login with correct credentials.");
        reportUtils.addTestLog(Status.INFO,"Performing Login");
        lp.enterLoginData(username,password);
        lp.clickLoginBtn();
        String expectedTitle = "PRODUCTS";
        String actualTitle = lp.validateLogin();
        reportUtils.addTestLog(Status.INFO,"Comparing expected and actual result.");
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @Parameters({"incorrect_username","incorrect_password"})
    @Test
    public void loginWithIncorrectCreds(String username,String password){

        reportUtils.createATestCase("Verify login with incorrect credentials.");
        reportUtils.addTestLog(Status.INFO,"Performing Login");
        lp.enterLoginData(username,password);
        lp.clickLoginBtn();
        String expectedTitle = "PRODUCTS";
        try{
            String actualTitle = lp.validateLogin();
            reportUtils.addTestLog(Status.INFO,"Comparing expected and actual result.");
            Assert.assertEquals(actualTitle,expectedTitle);

        }catch (Exception e){
            reportUtils.addTestLog(Status.INFO,"Unable to login.");
            Assert.assertFalse(false);

        }

    }

}
