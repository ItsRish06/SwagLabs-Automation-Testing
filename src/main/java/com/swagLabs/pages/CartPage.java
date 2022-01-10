package com.swagLabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    String name;
    LoginPage lp;

    @FindBy(xpath = "//div[@class=\"inventory_list\"]/div[1]/div[2]/div[2]/button")
    WebElement inventoryItem;

    @FindBy(xpath = "//div[@class=\"inventory_list\"]/div[1]/div[2]/div/a/div")
    WebElement inventoryItemName;

    @FindBy(xpath = "//a[@class=\"shopping_cart_link\"]")
    WebElement cart;

    @FindBy(xpath = "//div[@class=\"cart_item\"][1]/div[2]/a/div")
    WebElement cartItem;

    @FindBy(id="user-name")
    WebElement username;

    @FindBy(id="password")
    WebElement password;

    @FindBy(id="login-button")
    WebElement loginBtn;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppping;

    @FindBy(xpath = "//div[@class=\"cart_item\"]")
    List<WebElement> items;


    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        lp = new LoginPage(driver);
    }

    public String addToCart(){
        name = inventoryItemName.getText();
        elementControl.clickElement(inventoryItem);
        return name;
    }

    public void goToCart(){
        elementControl.clickElement(cart);
    }

    public String validateAddToCart(){
        return cartItem.getText();
    }

    public void login(){
        lp.enterLoginData("standard_user","secret_sauce");
        lp.clickLoginBtn();

    }

    public List<WebElement> removeAndValidate(){
        elementControl.clickElement(continueShoppping);
        elementControl.clickElement(inventoryItem);
        elementControl.clickElement(cart);
        return items;
    }

    public int numberOfItemsInCart(){
        return items.size();
    }

}
