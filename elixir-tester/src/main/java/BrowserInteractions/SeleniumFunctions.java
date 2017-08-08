package BrowserInteractions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumFunctions {

//    SeleniumFunctions seleniumFunctions;
//
//    public SeleniumFunctions(){
//        seleniumFunctions =  new SeleniumFunctions();
//    }
    static WebDriver driver;
        //WebDriver driver;

    public boolean open(String browser, String url){


//        DesiredCapabilities dc = DesiredCapabilities.chrome();
//        dc.setCapability("version","");
//        dc.setCapability("platform","LINUX");
//        System.setProperty("webdriver.chrome.driver", obj.PATH_CHROME_DRIVER);
//        WebDriver driver = new ChromeDriver();
//        try {
//            driver = new RemoteWebDriver(new URL("http://localhost:4446/wd/hub"), dc);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        System.setProperty("webdriver.chrome.driver", "./chromedriver");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get(url);
        return true;
    }

    public boolean click(String type, String element){
        this.returnWebElement(element,type).click();
        return true;
    }

    public boolean quit(){
        driver.quit();
        return true;
    }

    public boolean sendKeys(String type, String element, String text){
        this.returnWebElement(element,type).sendKeys(text.trim());
        return true;
    }

    public WebElement returnWebElement(String webElement, String type)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        int tryCount = 1;
        while (tryCount <= 2)
        {
            try
            {
                if (type.equalsIgnoreCase("linktext"))
                {
                    return	wait.until(ExpectedConditions.elementToBeClickable(By.linkText(webElement)));
                }
                else if (type.equalsIgnoreCase("xpath"))
                {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(webElement)));
                    //return driver.findElement(By.xpath(webElement));
                }
                else if (type.equalsIgnoreCase("name"))
                {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.name(webElement)));
                    //return driver.findElement(By.name(webElement));
                }
                else if (type.equalsIgnoreCase("id"))
                {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.id(webElement)));
                    //return driver.findElement(By.id(webElement));
                }
                else if (type.equalsIgnoreCase("classname"))
                {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.className(webElement)));
                    //return driver.findElement(By.className(webElement));
                }
                else if (type.equalsIgnoreCase("cssselector"))
                {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(webElement)));
                    //return driver.findElement(By.cssSelector(webElement));
                }
                else if (type.equalsIgnoreCase("partialLinkText"))
                {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(webElement)));
                    //return driver.findElement(By.partialLinkText(webElement));
                }
            }

            catch(Exception e)
            {
                // realtor.com has a pop up which comes up some times
                // need to enter the code to handle that
                if (type.equalsIgnoreCase("linktext")){
                    return	wait.until(ExpectedConditions.elementToBeClickable(By.linkText(webElement)));
                }
                else if (type.equalsIgnoreCase("xpath")){
                    return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(webElement)));
                    //return driver.findElement(By.xpath(webElement));
                }
                else if (type.equalsIgnoreCase("name")){
                    return wait.until(ExpectedConditions.elementToBeClickable(By.name(webElement)));
                    //return driver.findElement(By.name(webElement));
                }
                else if (type.equalsIgnoreCase("id")){
                    return wait.until(ExpectedConditions.elementToBeClickable(By.id(webElement)));
                    //return driver.findElement(By.id(webElement));
                }
                else if (type.equalsIgnoreCase("classname")){
                    return wait.until(ExpectedConditions.elementToBeClickable(By.className(webElement)));
                    //return driver.findElement(By.className(webElement));
                }
                else if (type.equalsIgnoreCase("cssselector")){
                    return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(webElement)));
                    //return driver.findElement(By.cssSelector(webElement));
                }
                else if (type.equalsIgnoreCase("partialLinkText")){
                    return wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(webElement)));
                    //return driver.findElement(By.partialLinkText(webElement));
                }
            }
        }
        return null;
    }
}
