package com.epam.pageObjectClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Pavan_Kotha
 *
 */
public abstract class Page {
    /**
     * contains action object
     */
    protected final Actions action;
    /**
     * contains web driver
     */
    protected final WebDriver driver;
    /**
     * @param driver contains the webdriver object.
     */
    public Page(WebDriver driver) {
            this.driver = driver;
            action = new Actions(driver); 
    }
    /**
     * @return the webdriver object.
     */
    public WebDriver getDriver() {
            return this.driver;
    }
    /**
     * @return the action object
     */
    public Actions getActions() {
        return this.action;
    }
    public WebDriverWait globalWait () {
    	return new WebDriverWait(driver, 4);
    }
    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementVisible(String cssLocator){
        return driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
    }
}
