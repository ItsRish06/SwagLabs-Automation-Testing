package commonLibs.implementations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementControl {
    WebDriver driver;

    public ElementControl(WebDriver driver){
        this.driver = driver;
    }

    public void clickElement(WebElement element){
        element.click();
    }

    public void clear(WebElement element){
        element.clear();
    }

    public void setText(WebElement element,String text){
        element.sendKeys(text);
    }

    public boolean isDisplayed(WebElement element){
        return element.isDisplayed();
    }

    public boolean isEnabled(WebElement element){
        return element.isEnabled();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    public String getTextFromAlert(){
        return driver.switchTo().alert().getText();
    }

    public void selectDropDown(WebElement element , String text){
        Select selDropDown = new Select(element);
        selDropDown.selectByVisibleText(text);
    }

    public String getTextFromElemX(String locator){
        return driver.findElement(By.xpath(locator)).getText();
    }


}
