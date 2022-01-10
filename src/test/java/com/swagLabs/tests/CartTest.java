package com.swagLabs.tests;

import com.aventstack.extentreports.Status;
import com.swagLabs.pages.CartPage;
import commonLibs.implementations.CommonDriver;
import commonLibs.utils.ReportUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class CartTest extends BaseTest {

    @BeforeClass
    public void reportSetup(){
        currentWorkingDirectory = System.getProperty("user.dir");
        reportFilename =currentWorkingDirectory+"/reports/swagLabsTestReport-Cart.html";
        reportUtils = new ReportUtils(reportFilename);
    }



    @BeforeMethod
    public void setup() throws Exception {
        url = "https://www.saucedemo.com/";
        String browserType = "chrome";
        cmnDriver = new CommonDriver(browserType);
        driver = cmnDriver.getDriver();
        cp = new CartPage(driver);
        cmnDriver.navigateToUrl(url);
    }


    @Test
    public void addToCart(){
        cp.login();
        reportUtils.createATestCase("Verify add to cart functionality.");
        reportUtils.addTestLog(Status.INFO,"Adding item to cart.");
        String expectedResult = cp.addToCart();
        reportUtils.addTestLog(Status.INFO,"Navigating to cart.");
        cp.goToCart();
        reportUtils.addTestLog(Status.INFO,"Validating cart item.");
        String actualResult = cp.validateAddToCart();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void removeFromCart(){
        cp.login();
        reportUtils.createATestCase("Verify remove from cart functionality.");
        reportUtils.addTestLog(Status.INFO,"Adding item to cart.");
        String expectedResult = cp.addToCart();
        reportUtils.addTestLog(Status.INFO,"Navigating to cart.");
        cp.goToCart();
        reportUtils.addTestLog(Status.INFO,"Number of items in cart : "+cp.numberOfItemsInCart());
        reportUtils.addTestLog(Status.INFO,"Removing item from cart.");
        List<WebElement> items = cp.removeAndValidate();
        reportUtils.addTestLog(Status.INFO,"Checking if item is removed.");
        reportUtils.addTestLog(Status.INFO,"Number of items in cart : "+cp.numberOfItemsInCart());
        Assert.assertEquals(items.size(),0);

    }
}
