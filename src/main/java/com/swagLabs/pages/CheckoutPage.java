package com.swagLabs.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Math.round;

public class CheckoutPage extends BasePage {
    CartPage cp;
    LoginPage lp;
    String itemPriceText;

    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(xpath = "//div[@class='header_secondary_container']/span")
    WebElement checkoutHeader;

    @FindBy(id="continue")
    WebElement overviewBtn;

    @FindBy(id="finish")
    WebElement finishBtn;

    @FindBy(xpath = "//div[@class=\"cart_item\"][1]/div[2]/div[2]/div")
    WebElement itemPrice;

    @FindBy(xpath = "//div[@class=\"summary_total_label\"]")
    WebElement itemTotal;


    public CheckoutPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        cp = new CartPage(driver);
        lp = new LoginPage(driver);
    }

    public String checkout() throws InterruptedException {
        lp.enterLoginData("standard_user","secret_sauce");
        lp.clickLoginBtn();
        cp.addToCart();
        cp.goToCart();
        itemPriceText = itemPrice.getText();
        elementControl.clickElement(checkoutBtn);
        return checkoutHeader.getText();
    }

    public String enterCheckoutDetails(){
        elementControl.setText(firstName,"Rishab");
        elementControl.setText(lastName,"Shetty");
        elementControl.setText(postalCode,"123345");
        overviewBtn.click();
        return checkoutHeader.getText();
    }

    public Boolean validateItemTotal(){
        int start = itemPriceText.indexOf("$");
        int start1 = itemTotal.getText().indexOf("$");
        Double num1 = Double.parseDouble(itemPriceText.substring(start+1));
        Double num2 = Double.parseDouble(itemTotal.getText().substring(start1+1));
        Double total = num1 + (0.08*num1);
        return (num2==round(total*100.0)/100.0);

    }

    public String checkoutOverviewFinish(){
        finishBtn.click();
        return checkoutHeader.getText();
    }

    public void checkoutWithNoItems(){
        lp.enterLoginData("standard_user","secret_sauce");
        lp.clickLoginBtn();
        cp.goToCart();
        elementControl.clickElement(checkoutBtn);
        enterCheckoutDetails();
        checkoutOverviewFinish();

    }

}
