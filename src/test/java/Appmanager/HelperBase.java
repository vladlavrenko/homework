package Appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

class HelperBase {
    WebDriver driver;

    HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    void type(By locator, String text) {
        if (text != null) {
            click(locator);
            String existingText = driver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    void attach(By locator, File file) {
        if (file != null) {
            driver.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    void click(By locator) {
        driver.findElement(locator).click();
    }

    boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
