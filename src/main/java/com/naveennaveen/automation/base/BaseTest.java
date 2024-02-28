package com.naveennaveen.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Test
public class BaseTest {
    protected static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browserType = "chrome";
        switch (browserType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                DriverManager.setDriver(new ChromeDriver());
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                DriverManager.setDriver(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Invalid browser type provided:" + browserType);
        }

        DriverManager.getDriver().manage().window().maximize();
        navigateToBaseUrl();
    }


    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    public void navigateToBaseUrl() {
        // Updated URL
        String baseUrl = "https://testpages.herokuapp.com/styled/tag/dynamic-table.html";
        DriverManager.getDriver().get(baseUrl);
    }


}
