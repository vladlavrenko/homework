package Appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

class HelperBase {
    WebDriver driver;

    HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = driver.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
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
