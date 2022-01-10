package com.swagLabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{


    @FindBy(id="user-name")
    WebElement username;

    @FindBy(id="password")
    WebElement password;

    @FindBy(id="login-button")
    WebElement loginBtn;

    @FindBy(xpath = "//div[@class=\"header_secondary_container\"]//span[1]")
    WebElement loginValidate;

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void enterLoginData(String uname,String pass){
        elementControl.setText(username,uname);
        elementControl.setText(password,pass);
    }

    public void clickLoginBtn(){
        elementControl.clickElement(loginBtn);
    }

    public String validateLogin(){
        return loginValidate.getText();
    }


}
