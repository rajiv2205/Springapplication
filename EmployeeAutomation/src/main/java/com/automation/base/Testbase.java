package com.automation.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;





public class Testbase {

    public static Properties config = null;
    public static Properties OR = null;
    public WebDriver wbDv = null;
    public static EventFiringWebDriver driver = null;

    @BeforeSuite
    public void intilize()
    {
        // loading all the configuration values
        try {
            
            wbDv= new FirefoxDriver();
            driver = new EventFiringWebDriver(wbDv);

            // Entering the website URL
            Reporter.log("Navigate to url");
            driver.navigate().to("http://192.168.2.24:11011/");

            // maximizing the Browser window
            driver.manage().window().maximize();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static WebElement getObject(String xpathKey) {

        // Initialize the xpath and Checking the type of the locator
        String strXpath = OR.getProperty(xpathKey);

        if (strXpath.startsWith("//")) {

            return driver.findElement(By.xpath(OR.getProperty(xpathKey).trim()));


        } else {

            return driver.findElement(By.cssSelector(OR.getProperty(xpathKey).trim()));
        }
    }

    @AfterSuite
    public void closeBrowser() {

        driver.close();
        driver.quit();
    }


}
