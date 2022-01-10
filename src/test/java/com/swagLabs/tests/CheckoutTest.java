package com.swagLabs.tests;

import com.aventstack.extentreports.Status;
import com.swagLabs.pages.CartPage;
import com.swagLabs.pages.CheckoutPage;
import commonLibs.implementations.CommonDriver;
import commonLibs.utils.ReportUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest{
    @BeforeClass
    public void reportSetup(){
        currentWorkingDirectory = System.getProperty("user.dir");
        reportFilename =currentWorkingDirectory+"/reports/swagLabsTestReport-Checkout.html";
        reportUtils = new ReportUtils(reportFilename);
    }



    @BeforeMethod
    public void setup() throws Exception {
        url = "https://www.saucedemo.com/";
        String browserType = "chrome";
        cmnDriver = new CommonDriver(browserType);
        driver = cmnDriver.getDriver();
        co = new CheckoutPage(driver);
        cmnDriver.navigateToUrl(url);
    }

    @Test
    public void checkout() throws InterruptedException {
        reportUtils.createATestCase("Move to Checkout.");
        reportUtils.addTestLog(Status.INFO,"Adding item to cart and proceeding to checkout.");
        String actualResult = co.checkout();
        System.out.println(actualResult);
        String expectedResult = "CHECKOUT: YOUR INFORMATION";
        Thread.sleep(3000);
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void validateOverview() throws InterruptedException {
        reportUtils.createATestCase("Validating overview page.");
        co.checkout();
        reportUtils.addTestLog(Status.INFO,"Entering details to proceed with checkout.");
        String actualResult = co.enterCheckoutDetails();
        String expectedResult = "CHECKOUT: OVERVIEW";
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void validateItemTotal() throws InterruptedException {
        reportUtils.createATestCase("Validation total checkout cost.");

        co.checkout();
        co.enterCheckoutDetails();
        reportUtils.addTestLog(Status.INFO,"Equating item cost with total displayed on checkout page.");

        Assert.assertTrue(co.validateItemTotal());
    }

    @Test
    public void checkOutComplete() throws InterruptedException {
        reportUtils.createATestCase("Completing checkout process");
        co.checkout();
        co.enterCheckoutDetails();
        String actualResult = co.checkoutOverviewFinish();
        String expectedResult = "CHECKOUT: COMPLETE!";
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void checkoutWithNoItem(){
        reportUtils.createATestCase("Verify checkout fails with no items in cart.");
        reportUtils.addTestLog(Status.INFO,"Directly proceeding with checkout with no items in cart.");
        try{
            co.checkoutWithNoItems();
            Assert.assertTrue(false);
            reportUtils.addTestLog(Status.FAIL,"User is able to checkout without any items in cart.");
        }catch (Exception e){
            Assert.assertTrue(true);
        }
    }
}
