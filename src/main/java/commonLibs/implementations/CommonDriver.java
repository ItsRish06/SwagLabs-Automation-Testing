package commonLibs.implementations;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class CommonDriver {
    private WebDriver driver;

    public CommonDriver(String browser) throws Exception{
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else{
            throw new Exception("Invalid browser type : "+browser);
        }
        driver.manage().window().maximize();
    }

    public WebDriver getDriver(){
        return this.driver;
    }


    public void navigateToUrl(String url){
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);
    }

    public void closeAllBrowsers(){
        driver.quit();
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
