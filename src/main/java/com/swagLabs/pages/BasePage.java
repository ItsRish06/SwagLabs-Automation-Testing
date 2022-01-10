package com.swagLabs.pages;

import commonLibs.implementations.ElementControl;
import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriver driver;

    public ElementControl elementControl;

    public BasePage(WebDriver driver){
        this.driver = driver;
        elementControl = new ElementControl(this.driver);
    }
}
