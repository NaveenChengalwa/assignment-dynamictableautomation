package com.naveennaveen.automation.base;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    //ThreadLocal class to store WebDriver instances.
    // This allows each thread to have its own WebDriver instance,
    // ensuring thread-safety in parallel test execution.

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThread.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }

}
